package net.melonclient.client.api.event.impl

import net.melonclient.client.api.event.type.CancellableEvent
import net.melonclient.client.api.event.type.PrePostEvent

open class KeyEvent: CancellableEvent() {
    
    private var keyCode: Int = -1
    private var inGame: Boolean = false
    
    fun getKeyCode(): Int {
        return keyCode
    }
    
    fun setKeyCode(keyCode: Int) {
        this.keyCode = keyCode
    }
    
    fun getInGame(): Boolean {
        return inGame
    }
    
    fun setInGame(inGame: Boolean) {
        this.inGame = inGame
    }
}

object KeyDownEvent: KeyEvent()
object KeyUpEvent: KeyEvent()