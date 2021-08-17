package net.prosperityclient.transformer.api

import net.melonclient.minecraft189.utils.asm.MethodNodeUtils
import net.minecraft.launchwrapper.IClassTransformer
import net.prosperityclient.transformer.dsl.injectInstructions
import net.prosperityclient.transformer.dsl.newMethod
import net.prosperityclient.utils.ClassManipulationUtil
import org.objectweb.asm.Opcodes
import org.objectweb.asm.tree.ClassNode
import org.objectweb.asm.tree.InsnNode
import org.objectweb.asm.tree.VarInsnNode

interface Transformer {
    
    val name: String
        get() = this::class.java.getAnnotation(TransformerInfo::class.java).name.qualifiedName!!
    
    val version: Double
    get() = this::class.java.getAnnotation(TransformerInfo::class.java).version
    
    
    fun transform(node: ClassNode)
    
    fun unwrap(): IClassTransformer = IClassTransformer { name, _, basicClass ->
        when (basicClass) {
            null -> {
                ByteArray(0)
            }
            else -> {
                if (name == Transformer::name.name) {
                    val node = ClassManipulationUtil.getNodeFromBytes(basicClass)
                    transform(node)
                    ClassManipulationUtil.getBytesFromNode(node)
                }
                basicClass
            }
        }
    }
}