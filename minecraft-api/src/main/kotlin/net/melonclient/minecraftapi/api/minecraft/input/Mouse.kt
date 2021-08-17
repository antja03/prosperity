package net.melonclient.minecraftapi.api.minecraft.input

interface Mouse {
    
    val x: Int
    
    val y: Int
    
    val dWheel: Int
    
    fun getPressed(button: MouseButton): Boolean
    
    
    enum class MouseButton {
        LEFT, RIGHT, MIDDLE
    }
}