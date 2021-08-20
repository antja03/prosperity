package net.melonclient.minecraft189.impl.lwjgl.display

import net.melonclient.minecraftapi.api.lwjgl.display.Display
import org.lwjgl.opengl.Display.setTitle

class Display : Display {
    override val width: Float
        get() = org.lwjgl.opengl.Display.getWidth().toFloat()
    override val height: Float
        get() = org.lwjgl.opengl.Display.getHeight().toFloat()
    override var screenTitle: String = org.lwjgl.opengl.Display.getTitle()
        set(value) {
            setTitle(value)
            field = value
        }
}