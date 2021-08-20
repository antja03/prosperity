package net.prosperityclient.tweaker.dsl

import org.objectweb.asm.Opcodes
import org.objectweb.asm.tree.ClassNode
import org.objectweb.asm.tree.MethodNode

fun ClassNode.newMethod(
    name: String,
    desc: String,
    access: Int = Opcodes.ACC_PUBLIC,
    init: MethodNode.() -> Unit
): MethodNode {
    val methodNode = MethodNode()
    methodNode.name = name
    methodNode.desc = desc
    methodNode.access = access
    methodNode.init()
    methods.add(methodNode)
    return methodNode
}