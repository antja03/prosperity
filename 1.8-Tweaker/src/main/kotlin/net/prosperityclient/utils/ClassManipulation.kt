package net.prosperityclient.utils

import org.objectweb.asm.ClassReader
import org.objectweb.asm.ClassWriter
import org.objectweb.asm.tree.*

object ClassManipulationUtil {
    
    fun getNodeFromBytes(bytes: ByteArray): ClassNode {
        val classReader = ClassReader(bytes)
        val node = ClassNode()
        classReader.accept(node, ClassReader.EXPAND_FRAMES)
        return node
    }
    
    fun getBytesFromNode(node: ClassNode): ByteArray {
        val classWriter = ClassWriter(ClassWriter.COMPUTE_FRAMES)
        node.accept(classWriter)
        return classWriter.toByteArray()
    }
}