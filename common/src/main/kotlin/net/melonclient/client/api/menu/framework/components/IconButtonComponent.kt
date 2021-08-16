package melon.api.menu.framework.components

import net.melonclient.client.theming.ThemeOwner
import net.melonclient.client.theming.registerThemeable
import net.melonclient.client.api.menu.framework.components.ButtonComponent
import net.melonclient.client.resource.ResourceManager
import net.melonclient.client.utils.Colors
import net.melonclient.client.utils.Cursor
import net.melonclient.client.utils.Render

open class IconButtonComponent(posX: Int, posY: Int, val imgName: String, val imgSize: Int, onClick: () -> Unit): ButtonComponent(posX, posY, 40, 40, onClick) {
    companion object: ThemeOwner {
        override val themeable = registerThemeable("melon.menu.iconbutton") themeable@ {}
        
        override fun setDefaultTheme() {
            themeable.setIntValue("menu.iconbutton.background.color", Colors.getRgba(30, 45, 30, 50))
            themeable.setIntValue("menu.iconbutton.background.hovercolor", Colors.getRgba(20, 45, 20, 100))
            themeable.setIntValue("menu.iconbutton.border.color", Colors.getRgba(100, 200, 100, 100))
            themeable.setIntValue("menu.iconbutton.border.hovercolor", Colors.getRgba(0, 255, 0, 150))
        }
        
        init {
            setDefaultTheme()
        }
    }
    
    override fun renderComponent() {
        val hovered = Cursor.isCursorInBounds(posX, posY, width, height)
        
        val backgroundColor = if (hovered) themeable.getIntValue("menu.iconbutton.background.hovercolor")!!
                              else themeable.getIntValue("menu.iconbutton.background.color")!!
        
        val borderColor = if (hovered) themeable.getIntValue("menu.iconbutton.border.hovercolor")!!
                          else themeable.getIntValue("menu.iconbutton.border.color")!!
        
        Render.drawRoundRectOutline(posX, posY, width, height, 1, 2f, borderColor)
        Render.drawRoundRect(posX, posY, width, height, 2f, backgroundColor)
    
        ResourceManager.getTextureId(imgName, imgSize).let {
            if (it != -1)
                if (hovered)
                    Render.drawImg(it, posX + 4, posY + 4, 32, 32, 0f, Colors.getRgba(255, 255, 255, 150))
                else
                    Render.drawImg(it, posX + 4, posY + 4, 32, 32)
        }
    }
}