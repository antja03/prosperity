package net.melonclient.minecraft189.impl.lwjgl

import net.melonclient.minecraftapi.LwjglApi
import net.melonclient.minecraftapi.api.lwjgl.display.Display
import net.melonclient.minecraftapi.api.lwjgl.opengl.ModernOpenGL
import net.melonclient.minecraftapi.api.lwjgl.opengl.Shaders
import net.melonclient.minecraftapi.api.lwjgl.opengl.Texture
import net.melonclient.minecraftapi.api.lwjgl.util.Buffer
import org.lwjgl.Sys

class LwjglApi : LwjglApi {
    override val intermediateOpenGL = net.melonclient.minecraft189.impl.lwjgl.opengl.IntermediateOpenGL()
    override val modernOpenGL: ModernOpenGL
        get() = TODO("Not yet implemented")
    override val shaders: Shaders
        get() = TODO("Not yet implemented")
    override val texture: Texture
        get() = TODO("Not yet implemented")
    override val display: Display
        get() = TODO("Not yet implemented")
    override val buffer: Buffer
        get() = TODO("Not yet implemented")
    override val version: String
        get() = Sys.getVersion()
}