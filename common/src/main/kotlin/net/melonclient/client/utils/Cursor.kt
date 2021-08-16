package net.melonclient.client.utils

import me.hippo.api.lwjeb.annotation.Handler
import net.melonclient.client.api.event.impl.StartFrameEvent
import net.melonclient.client.data.vec.Vec2

object Cursor {

    lateinit var cursorPos: Vec2<Int>
    lateinit var lastCursorPos: Vec2<Int>

    @Handler
    fun onStartFrame(event: StartFrameEvent) = run { }
//        if (this::cursorPos.isInitialized && this::lastCursorPos.isInitialized) {
//            lastCursorPos.x = cursorPos.x
//            lastCursorPos.y = cursorPos.y
//            cursorPos.x = Mouse.getX()
//            cursorPos.y = Minecraft.getMinecraft().displayHeight - Mouse.getY()
//        } else {
//            cursorPos = Vec2(Mouse.getX(), Minecraft.getMinecraft().displayHeight - Mouse.getY())
//            lastCursorPos = Vec2(Mouse.getX(), Minecraft.getMinecraft().displayHeight - Mouse.getY())
//        }

    fun getCursorPosition(): Vec2<Int> {
        return if (this::cursorPos.isInitialized)
            cursorPos
        else
            Vec2(-1, -1)
    }

    fun getLastCursorPosition(): Vec2<Int> {
        return if (this::lastCursorPos.isInitialized)
            lastCursorPos
        else
            Vec2(-1, -1)
    }

    fun isCursorInBounds(x: Int, y: Int, w: Int, h: Int): Boolean {
        val cursorPosition: Vec2<Int> = getCursorPosition()
        return cursorPosition.x >= x && cursorPosition.x <= x + w && cursorPosition.y >= y && cursorPosition.y <= y + h
    }
    
    fun release() {
//        Mouse.setGrabbed(false)
//        Minecraft.getMinecraft().setIngameNotInFocus()
    }
    
    fun grab() {
//        Mouse.setGrabbed(true)
//        Minecraft.getMinecraft().setIngameFocus()
    }
}