package net.melonclient.minecraftapi.api.minecraft.client

import net.melonclient.minecraftapi.api.minecraft.input.Keybind

interface MinecraftOptions {
    
    fun keybind(id: String): Keybind?
}