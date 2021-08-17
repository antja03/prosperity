package net.melonclient.minecraft189.impl.lwjgl

import net.melonclient.minecraftapi.LwjglApi
import net.melonclient.minecraftapi.api.lwjgl.display.Display
import net.melonclient.minecraftapi.api.lwjgl.opengl.Shaders
import net.melonclient.minecraftapi.api.lwjgl.util.Buffer
import org.lwjgl.Sys

class LwjglApi : LwjglApi {
    override val intermediateOpenGL = net.melonclient.minecraft189.impl.lwjgl.opengl.IntermediateOpenGL()
    override val modernOpenGL = net.melonclient.minecraft189.impl.lwjgl.opengl.ModernOpenGL()
    override val shaders: Shaders = net.melonclient.minecraft189.impl.lwjgl.opengl.Shaders()
    override val display = net.melonclient.minecraft189.impl.lwjgl.display.Display()
    override val buffer: Buffer = net.melonclient.minecraft189.impl.lwjgl.util.Buffer()
    override val version: String
        get() = Sys.getVersion()
}