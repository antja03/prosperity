package net.melonclient.minecraftapi

import net.melonclient.minecraftapi.api.minecraft.input.Keyboard
import net.melonclient.minecraftapi.api.minecraft.client.MinecraftClient
import net.melonclient.minecraftapi.api.minecraft.input.Mouse

interface MinecraftApi {
    
    val gameVersion: String
    
    val minecraftClient: MinecraftClient
    
    val keyboard: Keyboard
    
    val mouse: Mouse
    
}