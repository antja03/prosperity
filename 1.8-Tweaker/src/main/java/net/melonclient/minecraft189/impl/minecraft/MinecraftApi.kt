package net.melonclient.minecraft189.impl.minecraft

import net.melonclient.minecraftapi.MinecraftApi
import net.melonclient.minecraftapi.api.minecraft.client.MinecraftClient
import net.melonclient.minecraftapi.api.minecraft.input.Keyboard
import net.melonclient.minecraftapi.api.minecraft.input.Mouse

class MinecraftApi : MinecraftApi {
    override val gameVersion: String
        get() = "1.8.9"
    override val minecraftClient: MinecraftClient = net.melonclient.minecraft189.impl.minecraft.client.MinecraftClient()
    override val keyboard: Keyboard
        get() = net.melonclient.minecraft189.impl.minecraft.input.Keyboard
    override val mouse: Mouse
        get() = net.melonclient.minecraft189.impl.minecraft.input.Mouse
}