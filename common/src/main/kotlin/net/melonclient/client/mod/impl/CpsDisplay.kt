package net.melonclient.client.mod.impl

import me.hippo.api.lwjeb.annotation.Handler
import net.melonclient.client.Client
import net.melonclient.client.api.event.impl.MouseDownEvent
import net.melonclient.client.api.event.impl.RenderOverlayEvent
import net.melonclient.client.mod.Mod
import net.melonclient.client.utils.Colors
import net.melonclient.client.utils.Render

class CpsDisplay : Mod("default.cps_display") {
    
    private val lClickTimes = arrayListOf<Long>()
    private val rClickTimes = arrayListOf<Long>()
    
    @Handler
    fun onMouseDown(event: MouseDownEvent) {
        when (event.getMouseButton()) {
            0 -> lClickTimes.add(System.currentTimeMillis())
            1 -> rClickTimes.add(System.currentTimeMillis())
        }
    }
    
    @Handler
    fun onRenderOverlay(event: RenderOverlayEvent) {
        // Expire clicks
        lClickTimes.iterator().let {
            while (it.hasNext()) {
                val time = it.next()
                if (System.currentTimeMillis() - time > 1000)
                    it.remove()
            }
        }
        
        rClickTimes.iterator().let {
            while (it.hasNext()) {
                val time = it.next()
                if (System.currentTimeMillis() - time > 1000)
                    it.remove()
            }
        }
        
        val fontRenderer = Client.minecraftApi.minecraftClient.textRenderer
        val lString = "${lClickTimes.size}"
        val rString = "${rClickTimes.size}"
        
        Render.drawRect(10, 101, 31, 15, Colors.getRgba(0, 0, 0, 100))
        Render.drawRect(43, 101, 31, 15, Colors.getRgba(0, 0, 0, 100))
        
        fontRenderer.draw(
            lString,
            11 + 31 / 2 - fontRenderer.getStringWidth(lString) / 2,
            101 + 15 / 2 - fontRenderer.getStringHeight(lString) / 2,
            0xffffff
        )
        fontRenderer.draw(
            rString,
            44 + 31 / 2 - fontRenderer.getStringWidth(rString) / 2,
            101 + 15 / 2 - fontRenderer.getStringHeight(rString) / 2,
            0xffffff
        )
    }
}