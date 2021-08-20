package net.melonclient.client.mod.impl

import rip.hippo.lwjeb.annotation.Handler
import net.melonclient.client.api.event.impl.RenderOverlayEvent
import net.melonclient.client.mod.Mod
import net.melonclient.client.utils.Colors
import net.melonclient.client.utils.Render

class KeyStrokes: Mod("default.keystrokes") {
    
    @Handler
    fun onRenderOverlay(event: RenderOverlayEvent) {

        
        val keyColor = Colors.getRgba(0, 0, 0, 100)
        val keyPressColor = Colors.getRgba(0, 0, 0, 150)
        val labelColor = Colors.getRgb(255, 255, 255)
        
//        //W
//        Render.drawRect(32, 40, 20, 20, if (gameSettings.keyBindForward.isKeyDown) keyPressColor else keyColor)
//        fontRenderer.drawString("W", 42 - fontRenderer.getStringWidth("W") / 2, 50 - fontRenderer.FONT_HEIGHT / 2, labelColor)
//
//        //A
//        Render.drawRect(10, 62, 20, 20, if (gameSettings.keyBindLeft.isKeyDown) keyPressColor else keyColor)
//        fontRenderer.drawString("A", 20 - fontRenderer.getStringWidth("A") / 2, 72 - fontRenderer.FONT_HEIGHT / 2, labelColor)
//
//        //S
//        Render.drawRect(32, 62, 20, 20, if (gameSettings.keyBindBack.isKeyDown) keyPressColor else keyColor)
//        fontRenderer.drawString("S", 42 - fontRenderer.getStringWidth("S") / 2, 72 - fontRenderer.FONT_HEIGHT / 2, labelColor)
//
//        //D
//        Render.drawRect(54, 62, 20, 20, if (gameSettings.keyBindRight.isKeyDown) keyPressColor else keyColor)
//        fontRenderer.drawString("D", 64 - fontRenderer.getStringWidth("D") / 2, 72 - fontRenderer.FONT_HEIGHT / 2, labelColor)
//
//        //SPACE
//        Render.drawRect(10, 84, 64, 15, if (gameSettings.keyBindJump.isKeyDown) keyPressColor else keyColor)
//        fontRenderer.drawString("-", 42 - fontRenderer.getStringWidth("-") / 2, 94 - fontRenderer.FONT_HEIGHT / 2, labelColor)
    }
    
}