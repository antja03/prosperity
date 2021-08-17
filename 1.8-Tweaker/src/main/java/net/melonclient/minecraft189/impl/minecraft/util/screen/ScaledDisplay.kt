package net.melonclient.minecraft189.impl.minecraft.util.screen

import net.melonclient.minecraftapi.api.minecraft.util.screen.ScaledDisplay
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.ScaledResolution

object ScaledDisplay : ScaledDisplay {
    
    private val scaledResolution = ScaledResolution(Minecraft.getMinecraft())
    override val width: Float
        get() = scaledResolution.scaledWidth.toFloat()
    override val height: Float
        get() = scaledResolution.scaledHeight.toFloat()
    override val scaleFactor: Int
        get() = scaledResolution.scaleFactor
}