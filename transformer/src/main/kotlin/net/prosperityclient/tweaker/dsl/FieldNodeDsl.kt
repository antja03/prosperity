package net.prosperityclient.tweaker.dsl

import org.objectweb.asm.Opcodes
import org.objectweb.asm.tree.FieldNode

fun FieldNode.removeFinal() {
    if(this.access and Opcodes.ACC_FINAL != 0) {
        this.access = this.access.and(Opcodes.ACC_FINAL.inv())
    }
}