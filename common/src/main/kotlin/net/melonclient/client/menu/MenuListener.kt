package melon.impl.menu

import me.hippo.api.lwjeb.annotation.Handler
import net.melonclient.client.Client
import net.melonclient.client.api.event.impl.*

class MenuListener {
    private val menu = Menu()
    
    @Handler
    fun onRenderOverlay(event: RenderOverlayEvent) {
        // Swap to RenderScreenEvent
        if (Client.minecraftApi.minecraftClient.screen != null)
            return
        
        menu.render()
    }
    
    @Handler
    fun onRenderScreen(event: RenderScreenEvent) {
        menu.render()
    }
    
    @Handler
    fun onKeyDown(event: KeyDownEvent) {
        if (menu.onKeyDown(event.getKeyCode()))
            event.cancel()
    }
    
    @Handler
    fun onKeyUp(event: KeyUpEvent) {
        menu.onKeyUp(event.getKeyCode())
    }
    
    @Handler
    fun onMouseDown(event: MouseDownEvent) {
        if (menu.onMouseDown(event.getMouseButton()))
            event.cancel()
    }
    
    @Handler
    fun onMouseUp(event: MouseUpEvent) {
        menu.onMouseUp(event.getMouseButton())
    }
}