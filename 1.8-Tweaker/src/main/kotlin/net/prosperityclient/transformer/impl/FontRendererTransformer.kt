package net.prosperityclient.transformer.impl

import net.minecraft.client.gui.FontRenderer
import net.prosperityclient.transformer.api.Transformer
import net.prosperityclient.transformer.api.TransformerInfo
import net.prosperityclient.transformer.dsl.voidReturn
import net.prosperityclient.transformer.dsl.injectInstructions
import net.prosperityclient.transformer.dsl.newMethod
import org.objectweb.asm.Opcodes.*
import org.objectweb.asm.tree.ClassNode
import org.objectweb.asm.tree.InsnNode
import org.objectweb.asm.tree.MethodInsnNode
import org.objectweb.asm.tree.VarInsnNode

@TransformerInfo(FontRenderer::class, version = 1.0)
class FontRendererTransformer : Transformer {
    override fun transform(node: ClassNode) {
        with(node) {
            newMethod("draw",  "(Ljava/lang/String;FFI)V") {
                injectInstructions {
                    +VarInsnNode(ALOAD, 0)
                    +VarInsnNode(ALOAD, 1)
                    +VarInsnNode(FLOAD, 2)
                    +InsnNode(F2I)
                    +VarInsnNode(FLOAD, 3)
                    +InsnNode(F2I)
                    +VarInsnNode(ILOAD, 4)
                    +MethodInsnNode(INVOKEVIRTUAL, node.name,
                        "drawString", "(Ljava/lang/String;III)I", false)
                    voidReturn()
                }
            }
        }
    }
}