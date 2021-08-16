package melon.api.menu.framework.components

import net.melonclient.client.api.menu.framework.components.ButtonComponent
import net.melonclient.client.resource.ResourceManager
import net.melonclient.client.utils.*
import java.awt.Font
import java.awt.font.TextAttribute

open class IconTextButtonComponent(posX: Int, posY: Int, val text: String, val imgName: String, val imgSize: Int, val imgAngle: Float = 0f, onClick: () -> Unit): ButtonComponent(posX, posY, 240, 40, onClick) {
    companion object {
        var textRenderer: CustomTextRenderer? = null
    }
    
    init {
        if (textRenderer == null)
            textRenderer = CustomFontManager.get("Arial", 15, Font.PLAIN, TextAttribute.WEIGHT_BOLD)
    }
    
    override fun renderComponent() {
        val texId = ResourceManager.getTextureId(imgName, imgSize)
        val hovered = Cursor.isCursorInBounds(posX, posY, width, height)
        Render.drawRoundRectOutline(posX, posY, width, height, 1, 2f, if (hovered) Colors.getRgba(100, 255, 100, 100) else Colors.getRgba(200, 200, 200, 100))
        Render.drawRoundRect(posX, posY, width, height, 2f, Colors.getRgba(30, 30, 30, 50))
        Render.drawRect(posX + 49, posY + 5, 1, height - 10, Colors.getRgba(200, 200, 200, 50))
        Render.drawImg(texId, posX + 4, posY + 4, 32, 32, imgAngle)
        textRenderer?.draw(
            text,
            posX + 59f,
            posY + height / 2 - textRenderer!!.getStringHeight(text) / 2,
            Colors.getRgba(200, 200, 200, 200)
        )
    }
    
}