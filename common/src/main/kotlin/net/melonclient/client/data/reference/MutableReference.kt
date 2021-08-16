package net.melonclient.client.data.reference

import kotlin.properties.Delegates
import kotlin.reflect.KProperty

/**
   Example usage:
 
    val mutableReference = MutableReference(0)

    init {
        mutableReference.registerCallback({ _, old, new ->
            // Do something with the old and new value every time it changes
        })
    }
 */

open class MutableReference<T>(final override val defaultValue: T) : Reference<T> {
    
    private var callbacks: ArrayList<(KProperty<*>, T, T) -> Unit>? = null
    
    override var value: T by Delegates.observable(defaultValue) { property, old, new ->
        if (old != new && callbacks?.isNotEmpty() == true)
            for (callback in callbacks!!)
                callback.invoke(property, old, new)
    }
    
    fun registerCallback(callback: (KProperty<*>, T, T) -> Unit) {
        if (callbacks == null)
            callbacks = arrayListOf()
        callbacks!!.add(callback)
    }
    
    fun removeCallback(callback: (KProperty<*>, T, T) -> Unit) {
        if (callbacks == null)
            return
        
        callbacks!!.remove(callback)
        
        // Free memory everyone!! Get your free memory!!
        if (callbacks!!.isEmpty())
            callbacks = null
    }
    
    fun reset() {
        value = defaultValue
    }
}