package net.prosperityclient.tweaker.dsl

import org.objectweb.asm.Opcodes
import org.objectweb.asm.tree.ClassNode
import org.objectweb.asm.tree.MethodNode

fun ClassNode.newMethod(name: String, desc: String, init: (MethodNode) -> Unit) {
    val methodNode = MethodNode()
    methodNode.exceptions = ArrayList()
    methodNode.name = name
    methodNode.desc = desc
    methodNode.access = Opcodes.ACC_PUBLIC
    this.methods.add(methodNode)
    init(methodNode)
}

fun ClassNode.method(name: String, desc: String, init: (MethodNode) -> Unit) {
    this.methods.forEach {
        if(it.name == name && it.desc == desc) {
            init(it)
            return@forEach
        }
    }
}