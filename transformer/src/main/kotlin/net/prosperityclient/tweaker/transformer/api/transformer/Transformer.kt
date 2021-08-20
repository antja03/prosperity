package net.prosperityclient.tweaker.transformer.api.transformer

import org.objectweb.asm.tree.ClassNode

interface Transformer {
    
    val name: String
        get() = this::class.java.getAnnotation(TransformerInfo::class.java).name.qualifiedName!!
    
    val version: Double
    get() = this::class.java.getAnnotation(TransformerInfo::class.java).version
    
    
    fun transform(node: ClassNode)
    
}