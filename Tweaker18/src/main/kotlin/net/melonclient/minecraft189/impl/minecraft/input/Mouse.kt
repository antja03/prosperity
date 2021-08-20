package net.melonclient.minecraft189.impl.minecraft.input

import net.melonclient.minecraftapi.api.minecraft.input.Mouse

object Mouse : Mouse {
    override val x: Int
        get() = org.lwjgl.input.Mouse.getX()
    override val y: Int
        get() = org.lwjgl.input.Mouse.getY()
    override val dWheel: Int
        get() = org.lwjgl.input.Mouse.getDWheel()
    
    override fun getPressed(button: Mouse.MouseButton) = org.lwjgl.input.Mouse.isButtonDown(button.ordinal)
}