package net.melonclient.minecraft189.impl.minecraft.client

import net.melonclient.minecraftapi.api.minecraft.auth.Session
import net.melonclient.minecraftapi.api.minecraft.client.MinecraftClient
import net.melonclient.minecraftapi.api.minecraft.client.MinecraftOptions
import net.melonclient.minecraftapi.api.minecraft.entity.ClientPlayer
import net.melonclient.minecraftapi.api.minecraft.font.TextRenderer
import net.melonclient.minecraftapi.api.minecraft.ui.Screen
import net.melonclient.minecraftapi.api.minecraft.util.screen.ScaledDisplay
import net.minecraft.client.Minecraft

class MinecraftClient : MinecraftClient {
    override var session: Session = (Minecraft.getMinecraft().session as Session)

    override val scaledDisplay: ScaledDisplay
        get() = TODO("Not yet implemented")
    override val textRenderer: TextRenderer
        get() = TODO("Not yet implemented")
    override val clientPlayer: ClientPlayer?
        get() = TODO("Not yet implemented")
    override val options: MinecraftOptions
        get() = TODO("Not yet implemented")
    override val screen: Screen?
        get() = TODO("Not yet implemented")
    override val fps: Int
        get() = Minecraft.getDebugFPS()
    
    override fun toggleFullscreen() {
        Minecraft.getMinecraft().toggleFullscreen()
    }
}