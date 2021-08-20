package net.melonclient.client

import rip.hippo.lwjeb.annotation.Handler
import net.melonclient.client.api.event.impl.GameTickEvent
import net.melonclient.minecraftapi.api.minecraft.util.screen.ScaledDisplay


object Shared {
    
    // Shared instance of scaled resolution
    lateinit var scaledDisplay: ScaledDisplay
    
    @Handler
    fun onTick(event: GameTickEvent) {
        scaledDisplay = Client.minecraftApi.minecraftClient.scaledDisplay
    }
    
}