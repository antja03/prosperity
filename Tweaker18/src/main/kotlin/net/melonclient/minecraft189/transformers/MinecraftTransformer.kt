package net.melonclient.minecraft189.transformers

import jdk.internal.org.objectweb.asm.Opcodes
import net.minecraft.client.Minecraft
import net.prosperityclient.tweaker.dsl.*
import net.prosperityclient.tweaker.transformer.api.transformer.Transformer
import net.prosperityclient.tweaker.transformer.api.transformer.TransformerInfo
import org.objectweb.asm.tree.ClassNode
import java.io.PrintStream

@TransformerInfo(Minecraft::class, 1.0)
class MinecraftTransformer : Transformer {
    override fun transform(node: ClassNode) {
        node.method("startGame", "()V") {
            it.insert(it.instructions.first) {
                +field(Opcodes.GETSTATIC, System::class, "out", PrintStream::class)
                +string("Hello, Prosperity!")
                +method(Opcodes.INVOKEVIRTUAL, PrintStream::class, "println", arrayOf(String::class), Void.TYPE.kotlin)
            }
        }
    }
}