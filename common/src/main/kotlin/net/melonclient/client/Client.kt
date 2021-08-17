package net.melonclient.client

import me.hippo.api.lwjeb.annotation.Handler
import melon.impl.menu.MenuListener
import net.melonclient.client.api.event.impl.KeyDownEvent
import net.melonclient.client.mod.ModRegistry
import net.melonclient.minecraftapi.LwjglApi
import net.melonclient.minecraftapi.LwjglApiInit
import net.melonclient.minecraftapi.MinecraftApi
import net.melonclient.minecraftapi.MinecraftApiInit


/*

    TODO: Reference types
    TODO: Mod implementation @ every other dev LOL

 */

object Client {
    
    val minecraftApi: MinecraftApi
        get() = MinecraftApiInit.minecraftApi
    
    val lwjglApi: LwjglApi
        get() = LwjglApiInit.lwjglApi
    
    val modRegistry = ModRegistry()
    val menu = MenuListener()
    
    fun startup() {
        LwjglApiInit.lwjglApi.display.screenTitle = "Melon Client (Beta)"
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

