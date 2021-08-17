package net.melonclient.client.mod.impl

import me.hippo.api.lwjeb.annotation.Handler
import net.melonclient.client.Client
import net.melonclient.client.api.event.impl.GameTickEvent
import net.melonclient.client.api.event.impl.RenderOverlayEvent
import net.melonclient.client.mod.Mod

class ToggleSprint : Mod("default.toggle_sprint") {
    
    private var toggled: Boolean = false
    
    @Handler
    fun onGameTick(event: GameTickEvent) {
        val sprintKeybind = Client.minecraftApi.minecraftClient.options.keybind("sprint")
    
        if (sprintKeybind != null) {
            if (sprintKeybind.pressed)
                toggled = !toggled
        }
    
        if (sprintKeybind != null) {
            sprintKeybind.pressed = toggled
        }
    }
    
    @Handler
    fun onRenderOverlay(event: RenderOverlayEvent) {
        val stateString = if (toggled) "Toggled" else "Vanilla"
        with(Client.minecraftApi.minecraftClient) {
            if (clientPlayer?.isSprinting == true || toggled) {
                textRenderer.drawWithShadow("[Sprinting ($stateString)]", 10f, 10f, 0xffffff)
            }
        }
    }
}