package net.melonclient.client.api.menu.framework.components

import net.melonclient.client.data.reference.MutableReference
import net.melonclient.client.resource.ResourceManager
import net.melonclient.client.theming.ThemeOwner
import net.melonclient.client.theming.registerThemeable
import net.melonclient.client.utils.*
import java.awt.Font

class StatedIconTextButtonComponent(val state: MutableReference<Boolean>, posX: Int, posY: Int, width: Int, height: Int, val text: String, val imgName: String, val imgSize: Int, imgAngle: Float = 0f, onClick: () -> Unit): ButtonComponent(posX, posY, width, height, onClick){
    companion object: ThemeOwner {
        override val themeable = registerThemeable("melon.menu.statedicontextbutton") themeable@ {}
        var textRenderer: CustomTextRenderer? = null
        
        override fun setDefaultTheme() {
            themeable.setIntValue("background.color", Colors.getRgba(30, 30, 30, 50))
            themeable.setIntValue("background.selected.color", Colors.getRgba(30, 70, 30, 50))
            themeable.setIntValue("border.color", Colors.getRgba(200, 200, 200, 100))
            themeable.setIntValue("border.selected.color", Colors.getRgba(100, 200, 100, 100))
            themeable.setIntValue("separator.color", Colors.getRgba(200, 200, 200, 50))
            themeable.setIntValue("text.color", Colors.getRgba(200, 200, 200, 200))
            themeable.setFloatValue("hover.modifier", 0.7f)
        }
        
        init {
            setDefaultTheme()
        }
    }
    
    init {
        if (textRenderer == null)
            textRenderer = CustomFontManager.get("Verdana", 8, Font.PLAIN)
    }
    
    override fun renderComponent() {
      
        
        val texId = ResourceManager.getTextureId(imgName, imgSize)
        
        var borderColor = if (state.value) themeable.getIntValue("border.selected.color")!!
                          else themeable.getIntValue("border.color")!!
        
        var backgroundColor = if (state.value) themeable.getIntValue("background.selected.color")!!
                              else themeable.getIntValue("background.color")!!
        
        val separatorColor = themeable.getIntValue("separator.color")!!
        
        val textColor = themeable.getIntValue("text.color")!!
        
        if (Cursor.isCursorInBounds(posX, posY, width, height)) {
            val modifier = themeable.getFloatValue("hover.modifier")!!
            borderColor = Colors.getMultipliedColor(borderColor, modifier, false)
            backgroundColor = Colors.getMultipliedColor(backgroundColor, modifier, false)
        }
        
        val imgBorder = (height - imgSize) / 2
        
        Render.drawRoundRectOutline(posX, posY, width, height, 1, 2f, borderColor)
        Render.drawRoundRect(posX, posY, width, height, 2f, backgroundColor)
        Render.drawImg(texId, posX + imgBorder, posY + imgBorder, 32, 32)
        Render.drawRect(posX + imgBorder * 2 + imgSize , posY + 5, 1, height - 10, separatorColor)
        textRenderer?.draw(
            "$text",
            posX + imgBorder * 3f + imgSize,
            posY + height / 2 - textRenderer!!.getStringHeight(text) / 2,
            textColor
        )
    }
    
}