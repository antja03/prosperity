package net.melonclient.client.api.event.impl

import net.melonclient.client.api.event.type.CancellableEvent

open class MouseEvent: CancellableEvent() {
    
    private var mouseButton: Int = -1
    private var inGame: Boolean = false
    
    fun getMouseButton(): Int {
        return mouseButton
    }
    
    fun setMouseButton(keyCode: Int) {
        this.mouseButton = keyCode
    }
    
    fun getInGame(): Boolean {
        return inGame
    }
    
    fun setInGame(inGame: Boolean) {
        this.inGame = inGame
    }
}

object MouseDownEvent: MouseEvent()
object MouseUpEvent: MouseEvent()