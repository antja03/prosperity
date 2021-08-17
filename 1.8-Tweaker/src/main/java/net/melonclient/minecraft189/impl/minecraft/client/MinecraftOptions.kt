package net.melonclient.minecraft189.impl.minecraft.client

import net.melonclient.minecraftapi.api.minecraft.client.MinecraftOptions
import net.melonclient.minecraftapi.api.minecraft.input.Keybind
import net.minecraft.client.Minecraft

class MinecraftOptions : MinecraftOptions {
    override fun keybind(id: String): Keybind? {
        with(Minecraft.getMinecraft().gameSettings) {
            keyBindings.forEach {
                if(id == it.keyDescription.substring(4)) {
                    return@with it as Keybind
                }
            }
        }
        return null
    }
}