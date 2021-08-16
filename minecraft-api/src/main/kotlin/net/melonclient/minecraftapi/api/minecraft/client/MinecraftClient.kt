package net.melonclient.minecraftapi.api.minecraft.client

import net.melonclient.minecraftapi.api.minecraft.auth.Session
import net.melonclient.minecraftapi.api.minecraft.entity.ClientPlayer
import net.melonclient.minecraftapi.api.minecraft.font.TextRenderer
import net.melonclient.minecraftapi.api.minecraft.ui.Screen
import net.melonclient.minecraftapi.api.minecraft.util.screen.ScaledDisplay

interface MinecraftClient {
    
    var session: Session
    
    val scaledDisplay: ScaledDisplay
    
    val textRenderer: TextRenderer
    
    val clientPlayer: ClientPlayer?
    
    val options: MinecraftOptions
    
    val screen: Screen?
    
    val fps: Int
    
    fun toggleFullscreen()
}