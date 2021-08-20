package net.melonclient.minecraft189.transformers

import net.minecraft.client.Minecraft
import net.prosperityclient.tweaker.dsl.injectInstructions
import net.prosperityclient.tweaker.transformer.api.transformer.Transformer
import net.prosperityclient.tweaker.transformer.api.transformer.TransformerInfo
import org.objectweb.asm.Opcodes
import org.objectweb.asm.tree.ClassNode
import org.objectweb.asm.tree.FieldInsnNode
import org.objectweb.asm.tree.LdcInsnNode
import org.objectweb.asm.tree.MethodInsnNode

@TransformerInfo(Minecraft::class, 1.0)
class MinecraftTransformer : Transformer {
    override fun transform(node: ClassNode) {
        node.methods.forEach {
            if(it.name == "startGame") {
                it.injectInstructions(true) {
                    +FieldInsnNode(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;")
                    +LdcInsnNode("[Transformer]: Hello, World!")
                    +MethodInsnNode(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false)
                }
                return@forEach
            }
        }
    }
}