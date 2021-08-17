package net.melonclient.minecraftapi

import net.melonclient.minecraftapi.api.lwjgl.display.Display
import net.melonclient.minecraftapi.api.lwjgl.opengl.IntermediateOpenGL
import net.melonclient.minecraftapi.api.lwjgl.opengl.ModernOpenGL
import net.melonclient.minecraftapi.api.lwjgl.opengl.Shaders
import net.melonclient.minecraftapi.api.lwjgl.util.Buffer

interface LwjglApi {
    
    val intermediateOpenGL: IntermediateOpenGL
    
    val modernOpenGL: ModernOpenGL
    
    val shaders: Shaders
    
    val display: Display
    
    val buffer: Buffer
    
    val version: String
    
}