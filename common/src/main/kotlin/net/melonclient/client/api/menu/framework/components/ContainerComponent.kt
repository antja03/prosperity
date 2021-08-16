package net.melonclient.client.api.menu.framework.components

import net.melonclient.client.theming.ThemeOwner
import net.melonclient.client.theming.registerThemeable
import net.melonclient.client.api.menu.framework.Component
import net.melonclient.client.utils.Colors
import net.melonclient.client.utils.Render

class ContainerComponent(posX: Int, posY: Int, width: Int, height: Int): Component(posX, posY, width, height) {
    companion object: ThemeOwner {
        override val themeable = registerThemeable("melon.menu.container") themeable@ {}
        
        override fun setDefaultTheme() {
            themeable.setIntValue("menu.container.background.color", Colors.getRgba(25, 30, 25, 150))
        }
        
        init {
            setDefaultTheme()
        }
    }
    
    private val components = arrayListOf<Component>()
    
    override fun renderComponent() {
        Render.drawRoundRectOutline(posX - 1, posY - 1, width + 2, height + 2, 1, 2f, Colors.getRgba(0, 0, 0, 50))
        Render.drawRoundRectOutline(posX, posY, width, height, 1, 2f, Colors.getRgba(20, 20, 20, 100))
        Render.drawRoundRect(posX, posY, width, height, 2f, themeable.getIntValue("menu.container.background.color")!!)
        
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