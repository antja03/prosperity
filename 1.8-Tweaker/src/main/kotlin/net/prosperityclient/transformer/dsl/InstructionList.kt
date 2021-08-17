package net.prosperityclient.transformer.dsl

import net.prosperityclient.transformer.dsl.type.InstructionList
import org.objectweb.asm.Opcodes
import org.objectweb.asm.tree.InsnNode

fun InstructionList.floatCast() {
    +InsnNode(Opcodes.F2I)
}

fun InstructionList.voidReturn() {
    +InsnNode(Opcodes.RETURN)
}
