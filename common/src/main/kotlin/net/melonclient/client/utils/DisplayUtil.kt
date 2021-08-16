package net.melonclient.client.utils

import net.melonclient.client.Client
import kotlin.math.roundToInt

object DisplayUtil {
    
    class Resolution(val width: Int, val height: Int)
    
    private var unscaledResolution: Resolution? = null
    private var scaledResolution: Resolution? = null
    
    fun getResolution(): Resolution? {
        val screenWidth = Client.lwjglApi.display.width
        val screenHeight = Client.lwjglApi.display.height
        if (unscaledResolution == null || unscaledResolution?.width != screenWidth.roundToInt()|| unscaledResolution?.height != screenHeight.roundToInt()) {
            unscaledResolution = Resolution(screenWidth.roundToInt(), screenHeight.roundToInt())
        }
        return unscaledResolution
    }
    
    fun reverseY(y: Int): Int {
        return (Client.lwjglApi.display.height - y).roundToInt()
    }
    
    fun reverseScaledY(y: Int): Int {
        return (Client.minecraftApi.minecraftClient.scaledDisplay.width - y).roundToInt()
    }
    
    fun setTitle(title: String) {
        Client.lwjglApi.display.screenTitle = title
    }
    
    fun inScale(
        displayWidth: Float,
        displayHeight: Float,
        scaledWidth: Float,
        scaledHeight: Float,
        render: () -> Unit
    ) {
        with(Client.lwjglApi.intermediateOpenGL) {
            setupOrtho(displayWidth, displayHeight)
            render()
            setupOrtho(scaledWidth, scaledHeight)
        }
    }
    
}