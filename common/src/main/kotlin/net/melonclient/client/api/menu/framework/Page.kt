package net.melonclient.client.api.menu.framework

open class Page(posX: Int, posY: Int, width: Int, height: Int): Component(posX, posY, width, height) {
    
    private val components = arrayListOf<Component>()
    
    override fun renderComponent() {
        for (component in components)
            component.renderComponent()
    }
    
    override fun onMouseDown(mouseButton: Int): Boolean {
        for (component in components)
            if (component.onMouseDown(mouseButton))
                return true
        return false
    }
    
    override fun move(difX: Int, difY: Int) {
        posX += difX
        posY += difY
        
        for (component in components) {
            component.move(difX, difY)
        }
    }
    
    fun addComponent(component: Component) {
        components.add(component)
    }
}