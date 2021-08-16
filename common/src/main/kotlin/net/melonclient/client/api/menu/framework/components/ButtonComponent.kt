package net.melonclient.client.api.menu.framework.components

import net.melonclient.client.theming.ThemeOwner
import net.melonclient.client.theming.registerThemeable
import net.melonclient.client.api.menu.framework.Component
import net.melonclient.client.utils.Colors
import net.melonclient.client.utils.Cursor
import net.melonclient.client.utils.Render

open class ButtonComponent(posX: Int, posY: Int, width: Int, height: Int, val onClick: () -> Unit): Component(posX, posY, width, height) {
    companion object: ThemeOwner {
        override val themeable = registerThemeable("melon.menu.button") themeable@ {}
        
        override fun setDefaultTheme() {
            themeable.setIntValue("menu.button.background.color", Colors.getRgba(30, 45, 30, 50))
            themeable.setIntValue("menu.button.hover.color", Colors.getRgba(20, 45, 20, 100))
        }
        
        init {
            setDefaultTheme()
        }
    }

    override fun renderComponent() {
        val hovered = Cursor.isCursorInBounds(posX, posY, width, height)
        Render.drawRoundRectOutline(posX, posY, width, height, 1, 2f, if (hovered) Colors.getRgba(0, 200, 0, 100) else Colors.getRgba(200, 200, 200, 100))
        Render.drawRoundRect(posX, posY, width, height, 2f, if (hovered) themeable.getIntValue("menu.button.hover.color")!! else themeable.getIntValue("menu.button.background.color")!!)
    }
    
    override fun onMouseDown(mouseButton: Int): Boolean {
        if (Cursor.isCursorInBounds(posX, posY, width, height))
            return true.also { onClick.invoke() }
        return false
    }
    
}