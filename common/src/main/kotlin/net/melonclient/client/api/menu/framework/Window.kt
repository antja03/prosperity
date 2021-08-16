package net.melonclient.client.api.menu.framework

import net.melonclient.client.resource.ResourceManager
import net.melonclient.client.theming.ThemeOwner
import net.melonclient.client.theming.registerThemeable
import net.melonclient.client.utils.Colors
import net.melonclient.client.utils.Cursor
import net.melonclient.client.utils.DisplayUtil
import net.melonclient.client.utils.Render
import java.awt.Color

class Window(val width: Int, val height: Int) {
    companion object : ThemeOwner {
        override val themeable = registerThemeable("melon.menu.window") themeable@{}
        
        override fun setDefaultTheme() {
            themeable.setIntValue("menu.window.background.color", Colors.getRgba(20, 40, 20, 100))
        }
        
        init {
            setDefaultTheme()
        }
    }
    
    var posX = 0
    var posY = 0
    var grabbed = false
    val topPage = NestablePage(posX, posY, width, height)
    
    init {
        DisplayUtil.getResolution()?.let { resolution ->
            move(
                (resolution.width / 2 - width / 2),
                (resolution.height / 2 - height / 2)
            )
        }
    }
    
    
    fun renderWindow() {
        if (grabbed) {
            val mousePos = Cursor.getCursorPosition()
            val lastMousePos = Cursor.getLastCursorPosition()
            move(mousePos.x - lastMousePos.x, mousePos.y - lastMousePos.y)
        }
        
        Render.drawRoundRectOutline(posX - 2, posY - 2, width + 4, height + 4, 1, 4f, Colors.getRgba(0, 0, 0, 50))
        Render.drawRoundRectOutline(posX - 1, posY - 1, width + 2, height + 2, 1, 4f, Colors.getRgba(20, 20, 20, 50))
        Render.drawRoundRectOutline(posX, posY, width, height, 1, 2f, Colors.getRgba(20, 20, 20, 100))
        Render.drawRoundRect(posX, posY, width, height, 2f, themeable.getIntValue("menu.window.background.color")!!)
        val color1 = Colors.getRgba(0, 0, 0, 50)
        
        Render.drawRect(10, 10, 20, 20, Color.white.rgb)
        
//        rectangles {
//            render(
//                Vector4f(posX.toFloat() - 2, posY.toFloat() - 2, width.toFloat() + 4, height.toFloat() + 4),
//                Vector4f(0f, 0f, 0f, 50 / 255f),
//                1f
//            )
//            render(
//                Vector4f(posX.toFloat() - 1, posY.toFloat() - 1, width.toFloat() + 2, height.toFloat() + 2),
//                Vector4f(20f / 255f, 20f / 255f, 20f / 255f, 50 / 255f),
//                1f
//            )
//            render(
//                Vector4f(posX.toFloat(), posY.toFloat(), width.toFloat(), height.toFloat()),
//                Vector4f(20f / 255f, 20f / 255f, 20f / 255f, 100 / 255f),
//                1f
//            )
//        }
        Render.drawImg(ResourceManager.getTextureId("logo", 48), posX + 10, posY + 10, 48, 48, 90f)
        topPage.renderComponent()
    }
    
    fun onKeyDown(keyCode: Int) {
        topPage.onKeyDown(keyCode)
    }
    
    fun onKeyUp(keyCode: Int) {
        topPage.onKeyUp(keyCode)
    }
    
    fun onMouseDown(mouseButton: Int) {
        if (topPage.onMouseDown(mouseButton))
            return
        
        if (Cursor.isCursorInBounds(posX, posY, width, height))
            grabbed = true
    }
    
    fun onMouseUp(mouseButton: Int) {
        topPage.onMouseUp(mouseButton)
        grabbed = false
    }
    
    fun move(difX: Int, difY: Int) {
        posX += difX
        posY += difY
        topPage.move(difX, difY)
    }
}