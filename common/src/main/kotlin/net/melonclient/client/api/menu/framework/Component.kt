package net.melonclient.client.api.menu.framework

abstract class Component(var posX: Int, var posY: Int, val width: Int, val height: Int) {
    
    abstract fun renderComponent()
    
    fun renderComponent(posX: Int, posY: Int) {
        this.posX = posX
        this.posY = posY
        renderComponent()
    }
    
    fun onKeyDown(keyCode: Int): Boolean {
        return false
    }
    
    fun onKeyUp(keyCode: Int): Boolean {
        return false
    }
    
    open fun onMouseDown(mouseButton: Int): Boolean {
        return false
    }
    
    fun onMouseUp(mouseButton: Int): Boolean {
        return false
    }
    
    open fun move(difX: Int, difY: Int) {
        posX += difX
        posY += difY
    }
    
}