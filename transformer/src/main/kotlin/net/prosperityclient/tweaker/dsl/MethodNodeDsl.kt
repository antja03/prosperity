package net.prosperityclient.tweaker.dsl

import net.prosperityclient.tweaker.dsl.type.InstructionList
import org.objectweb.asm.tree.AbstractInsnNode
import org.objectweb.asm.tree.LabelNode
import org.objectweb.asm.tree.MethodNode

fun MethodNode.insert(
    insn: AbstractInsnNode,
    after: Boolean = false,
    searchLabel: Boolean = true,
    add: InstructionList.() -> Unit
) {
    val list = InstructionList()
    list.add()
    if (after) {
        if (searchLabel) {
            var insn = insn
            if (insn !is LabelNode) {
                do {
                    insn = insn.next
                } while (insn !is LabelNode)
            }

            list.insert(LabelNode())
            this.instructions.insert(insn, list)
        } else {
            this.instructions.insert(insn, list)
        }
    } else {
        if (searchLabel) {
            var insn = insn
            if (insn !is LabelNode) {
                do {
                    insn = insn.previous
                } while (insn !is LabelNode)
            }

            list.insertBefore(list.first, LabelNode())
            this.instructions.insertBefore(insn, list)
        } else {
            this.instructions.insertBefore(insn, list)
        }
    }
}