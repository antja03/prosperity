package net.melonclient.client.mod

import net.melonclient.client.api.event.EventBus
import net.melonclient.client.data.reference.MutableReference
import net.melonclient.client.data.setting.Setting

open class Mod(val id: String) {
    
    lateinit var displayName: String
    lateinit var description: String
    lateinit var settings: ArrayList<Setting<*>>
    
    private val state = MutableReference(false)
    
    init {
        state.registerCallback { _, _, new ->
            if (new) {
                onEnable()
                EventBus.pubSub.subscribe(this)
            } else {
                onDisable()
                EventBus.pubSub.unsubscribe(this)
            }
        }
    }
    
    fun onEnable() {}
    
    fun onDisable() {}
    
    fun isEnabled(): Boolean {
        return state.value
    }
    
    fun setEnabled(value: Boolean) {
        state.value = value
    }
    
    fun toggleEnabled() {
        state.value = !state.value
    }
    
    fun getState(): MutableReference<Boolean> {
        return state
    }
}