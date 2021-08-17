package net.melonclient.minecraftapi.api.minecraft.input

interface Keyboard {
    
    val keyMap: MutableMap<String, Int>
    
    fun getKeyHeld(key: Int): Boolean
    
    fun enableRepeatEvents(enable: Boolean)
}