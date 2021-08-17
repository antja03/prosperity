package net.prosperityclient.transformer.dsl

import net.melonclient.minecraft189.utils.asm.MethodNodeUtils
import org.objectweb.asm.tree.ClassNode
import org.objectweb.asm.tree.MethodNode

fun ClassNode.newMethod(name: String, desc: String, init: MethodNode.() -> Unit): MethodNode {
    val methodNode = MethodNodeUtils.newMethodNode(name, desc)
    methodNode.init()
    methods.add(methodNode)
    return methodNode
}