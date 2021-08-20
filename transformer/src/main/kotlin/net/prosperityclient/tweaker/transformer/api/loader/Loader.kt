package net.prosperityclient.tweaker.transformer.api.loader

import net.prosperityclient.tweaker.transformer.api.transformer.Transformer

interface Loader {
    
    fun add(transformer: Transformer)
}