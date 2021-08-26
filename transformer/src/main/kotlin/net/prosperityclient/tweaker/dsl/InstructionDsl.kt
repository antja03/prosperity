package net.prosperityclient.tweaker.dsl

import org.objectweb.asm.Opcodes
import org.objectweb.asm.Type
import org.objectweb.asm.tree.*
import kotlin.reflect.KClass

fun number(number: Number): AbstractInsnNode = when (number) {
    is Long -> {
        LdcInsnNode(number.toLong())
    }
    is Double -> {
        LdcInsnNode(number.toDouble())
    }
    is Float -> {
        LdcInsnNode(number.toFloat())
    }
    else -> {
        numberInt(number.toInt())
    }
}

fun `var`(type: Int, opcode: Int) = VarInsnNode(opcode, type)

fun field(opcode: Int, owner: KClass<*>, name: String, type: KClass<*>) = FieldInsnNode(
    opcode,
    owner.qualifiedName!!.replace(".", "/", true), name, Type.getDescriptor(type.java)
)

fun field(opcode: Int, owner: String, node: FieldNode) = FieldInsnNode(opcode, owner, node.name, node.desc)
fun method(opcode: Int, owner: KClass<*>, name: String, desc: Array<KClass<*>>, ret: KClass<*>): MethodInsnNode {
    val descRet = StringBuilder()
    @Suppress("NAME_SHADOWING") val ret = Type.getDescriptor(ret.java)
    if (desc.isEmpty()) {
        descRet.append("()").append(ret)
    } else {
        descRet.append("(")
        desc.forEach {
            descRet.append(Type.getDescriptor(it.java))
        }
        descRet.append(")").append(ret)
    }
    return MethodInsnNode(opcode, owner.qualifiedName!!.replace(".", "/", true), name, descRet.toString(), false)
}

fun label() = LabelNode()
fun string(name: String) = LdcInsnNode(name)

fun insn(opcode: Int) = InsnNode(opcode)

fun numberInt(number: Int): AbstractInsnNode {

    if (number > Byte.MIN_VALUE && number < Byte.MAX_VALUE) {
        return IntInsnNode(Opcodes.BIPUSH, number)
    }
    if (number > Short.MIN_VALUE && number < Short.MAX_VALUE) {
        return IntInsnNode(Opcodes.SIPUSH, number)
    }
    return LdcInsnNode(number)
}

