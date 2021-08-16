package net.melonclient.client

import me.hippo.api.lwjeb.annotation.Handler
import melon.impl.menu.MenuListener
import net.melonclient.client.api.event.EventBus
import net.melonclient.client.api.event.impl.KeyDownEvent
import net.melonclient.client.mod.ModRegistry
import net.melonclient.client.resource.ResourceManager
import net.melonclient.client.utils.Cursor
import net.melonclient.minecraftapi.LwjglApi
import net.melonclient.minecraftapi.LwjglApiInterfacingAgent
import net.melonclient.minecraftapi.MinecraftApi
import net.melonclient.minecraftapi.MinecraftApiInterfacingAgent
import net.melonclient.minecraftapi.api.minecraft.auth.Session


/*

    TODO: Reference types
    TODO: Mod implementation @ every other dev LOL

 */

object Client {
    
    val modRegistry = ModRegistry()
    val menu = MenuListener()
    val minecraftApi = MinecraftApiInterfacingAgent.minecraftApi
    val lwjglApi = LwjglApiInterfacingAgent.lwjglApi
    fun startup() {
//        minecraftApi.minecraftClient.session = Session() {
//
//        }
//        ResourceManager.startup()
//        EventBus.pubSub.subscribe(this)
//        EventBus.pubSub.subscribe(Shared)
//        EventBus.pubSub.subscribe(Cursor)
//        EventBus.pubSub.subscribe(menu)
    }
    
    @Handler
    fun onKeyDown(event: KeyDownEvent) {
//        if (event.getKeyCode() == Keyboard.KEY_F) {
//            modRegistry.getModByClass(ToggleSprint::class.java)?.let {
//                it.toggleEnabled()
//            }
//        }
    }
    
}

