package net.prosperityclient.tweaker.dsl

import net.prosperityclient.tweaker.dsl.type.InstructionList
import org.objectweb.asm.Opcodes
import org.objectweb.asm.tree.InsnNode

fun InstructionList.voidReturn() {
    +InsnNode(Opcodes.RETURN)
}
