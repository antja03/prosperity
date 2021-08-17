package net.prosperityclient.transformer.dsl

import net.prosperityclient.transformer.dsl.type.InstructionList
import org.objectweb.asm.tree.MethodNode

fun MethodNode.injectInstructions(add: InstructionList.() -> Unit = {}) {
    val list = InstructionList()
    list.add()
    instructions.add(list)
}