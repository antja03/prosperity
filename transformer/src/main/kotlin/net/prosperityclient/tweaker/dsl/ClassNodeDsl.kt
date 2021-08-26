package net.prosperityclient.tweaker.dsl

import org.objectweb.asm.Opcodes
import org.objectweb.asm.Type
import org.objectweb.asm.tree.ClassNode
import org.objectweb.asm.tree.FieldNode
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
        if (it.name == name && it.desc == desc) {
            init(it)
            return@forEach
        }
    }
}

fun ClassNode.field(name: String, desc: String, init: (FieldNode) -> Unit) {
    this.fields.forEach {
        if (it.name == name && it.desc == desc) {
            init(it)
            return@forEach
        }
    }
}

fun ClassNode.getterMethod(node: FieldNode) {
    val methodName = "get${node.name.methodName()}"
    val classNode = this
    val type = Type.getType(node.desc)
    val returnOpcode = type.getOpcode(Opcodes.IRETURN)
    this.newMethod(methodName, "()${node.desc}") {
        it.insert {
            +label() // Label node
            +`var`(0, Opcodes.ALOAD) // this
            +field(Opcodes.GETFIELD, classNode.name, node)
            +insn(returnOpcode)
        }
    }
}

fun ClassNode.setterMethod(node: FieldNode) {
    node.removeFinal()
    val methodName = "set${node.name.methodName()}"
    val classNode = this
    val type = Type.getType(node.desc)
    val loadOpcode = type.getOpcode(Opcodes.ILOAD)
    this.newMethod(methodName, "(${node.desc})V") {
        it.insert {
            +label() // labelnode
            +`var`(0, Opcodes.ALOAD) // load 'this'
            +`var`(1, loadOpcode) // load first paramater
            +field(Opcodes.PUTFIELD, classNode.name, node) // put paramater onto field
            +insn(Opcodes.RETURN) // return out of method
        }
    }
}

fun String.methodName(): String {
    var methodName = this

    if (this.contains("_")) {
        val split = this.split("_")
        methodName = ""
        for (s in split) {
            val array = s.lowercase().toCharArray()
            array[0] = array[0].uppercaseChar()
            methodName += array.toString()
        }
    }
    return methodName
}