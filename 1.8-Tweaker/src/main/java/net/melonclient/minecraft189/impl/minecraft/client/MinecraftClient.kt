package net.melonclient.minecraft189.impl.minecraft.client

import net.melonclient.minecraftapi.api.minecraft.auth.Session
import net.melonclient.minecraftapi.api.minecraft.client.MinecraftClient
import net.melonclient.minecraftapi.api.minecraft.client.MinecraftOptions
import net.melonclient.minecraftapi.api.minecraft.entity.ClientPlayer
import net.melonclient.minecraftapi.api.minecraft.font.TextRenderer
import net.melonclient.minecraftapi.api.minecraft.util.screen.ScaledDisplay
import net.minecraft.client.Minecraft

class MinecraftClient : MinecraftClient {
    override var session: Session = (Minecraft.getMinecraft().session as Session)
    
    override val scaledDisplay: ScaledDisplay = net.melonclient.minecraft189.impl.minecraft.util.screen.ScaledDisplay
    override val textRenderer: TextRenderer = (Minecraft.getMinecraft().fontRendererObj as TextRenderer)
    override val clientPlayer: ClientPlayer? = (Minecraft.getMinecraft().thePlayer as ClientPlayer?)
    override val options: MinecraftOptions = MinecraftOptions()
    
    override val inGame: Boolean
        get() = Minecraft.getMinecraft().currentScreen == null
    
    
    override val fps: Int
        get() = Minecraft.getDebugFPS()
    
    override fun toggleFullscreen() {
        Minecraft.getMinecraft().toggleFullscreen()
    }
}