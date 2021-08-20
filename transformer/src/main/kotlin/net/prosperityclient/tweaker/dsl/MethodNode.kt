package net.prosperityclient.tweaker.dsl

import net.prosperityclient.tweaker.dsl.type.InstructionList
import org.objectweb.asm.tree.MethodNode

fun MethodNode.injectInstructions(first: Boolean = false, add: InstructionList.() -> Unit = {}) {
    val list = InstructionList()
    list.add()
    if (first) {
        instructions.insertBefore(instructions.first, list)
    } else {
        instructions.insert(list)
    }
}