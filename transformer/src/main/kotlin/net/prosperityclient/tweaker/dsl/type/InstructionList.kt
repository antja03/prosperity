package net.prosperityclient.tweaker.dsl.type

import org.objectweb.asm.tree.AbstractInsnNode
import org.objectweb.asm.tree.InsnList

class InstructionList: InsnList() {
    
    operator fun AbstractInsnNode.unaryPlus() {
       add(this)
    }
    
}