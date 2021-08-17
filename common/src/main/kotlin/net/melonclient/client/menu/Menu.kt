package melon.impl.menu

import net.melonclient.client.Client
import net.melonclient.client.Shared
import net.melonclient.client.api.menu.dsl.*
import net.melonclient.client.data.reference.MutableReference
import net.melonclient.client.mod.impl.CpsDisplay
import net.melonclient.client.mod.impl.KeyStrokes
import net.melonclient.client.mod.impl.ToggleSprint
import net.melonclient.client.utils.Cursor
import net.melonclient.client.utils.DisplayUtil
class Menu {
    
    val profile1State = MutableReference(true)
    val profile2State = MutableReference(false)
    val profile3State = MutableReference(false)
    
    private val mainWindow = window(900, 550) {
        val window = this
        
        val modsPage = page(70, 70, 820, 470) {
            container(0, 0, 820, 470) {
                Client.modRegistry.getModByClass(ToggleSprint::class.java)?.let { mod ->
                    statedIconButtonWithText(mod.getState(), 25, 20, 240, 50, "Toggle Sprint", "sprinting_person", 32) {
                        mod.toggleEnabled()
                    }
                }
                
                Client.modRegistry.getModByClass(CpsDisplay::class.java)?.let { mod ->
                    statedIconButtonWithText(mod.getState(), 290, 20, 240, 50, "CPS Display", "cursor_click", 32) {
                        mod.toggleEnabled()
                    }
                }
                
                Client.modRegistry.getModByClass(KeyStrokes::class.java)?.let { mod ->
                    statedIconButtonWithText(mod.getState(), 555, 20, 240, 50, "Keystrokes", "wasd", 32) {
                        mod.toggleEnabled()
                    }
                }
                
                statedIconButtonWithText(profile3State, 25, 95, 240, 50, "Replay Mod", "replay", 32)
                statedIconButtonWithText(profile3State, 290, 95, 240, 50, "Old Animations", "pickaxe", 32)
                statedIconButtonWithText(profile3State, 555, 95, 240, 50, "Item Physics", "apple_fall", 32)
                statedIconButtonWithText(profile3State, 25, 170, 240, 50, "Perspective", "360", 32)
                statedIconButtonWithText(profile3State, 290, 170, 240, 50, "Discord RPC", "discord_1", 32)
                statedIconButtonWithText(profile3State, 555, 170, 240, 50, "Ping Display", "ping", 32)
            }
        }
        
        val themesPage = page(70, 70, 820, 470) {
            container(0, 0, 820, 470) {
            
            }
        }
        
        val cosmeticsPage = page(70, 70, 820, 470) {
            container(0, 0, 820, 470) {
            
            }
        }
        
        val chatPage = page(70, 70, 820, 470) {
            container(0, 0, 820, 470) {
            
            }
        }
        
        // Tabs container
        container(10, 70, 50, 470) {
            iconButton(5, 10, "grid", 32) {
                window.topPage.setPage(modsPage)
                println("clicked that hoe on god")
            }
            iconButton(5, 60, "paint_drop", 32) {
                window.topPage.setPage(themesPage)
            }
            iconButton(5, 110, "cape", 32) {
                window.topPage.setPage(cosmeticsPage)
            }
            iconButton(5, 160, "message", 32) {
                window.topPage.setPage(chatPage)
            }
            iconButton(5, 420, "close", 32) {
                displayMainWindow.value = false
            }
        }
        
        // Profiles container
        container(70, 10, 820, 50) {
            statedIconButtonWithText(profile1State, 10, 5, "Profile 1", "person", 32) {
                profile1State.value = true
                profile2State.value = false
                profile3State.value = false
            }
            
            statedIconButtonWithText(profile2State, 260, 5, "Profile 2", "person", 32) {
                profile1State.value = false
                profile2State.value = true
                profile3State.value = false
            }
            
            statedIconButtonWithText(profile3State, 520, 5, "Profile 3", "person", 32) {
                profile1State.value = false
                profile2State.value = false
                profile3State.value = true
            }
        }
        
        this.topPage.setPage(modsPage)
    }
    
    private var displayMainWindow = MutableReference(false)
    
    init {
        displayMainWindow.registerCallback { _, _, new ->
            if (new) {
                if (Client.minecraftApi.minecraftClient.inGame) {
                    Cursor.release()
                }
            } else {
                if (Client.minecraftApi.minecraftClient.inGame == null) {
                    Cursor.grab()
                }
            }
        }
    }
    
    fun render() {
        if (!displayMainWindow.value)
            return
        
        DisplayUtil.inScale(
            Client.lwjglApi.display.width,
            Client.lwjglApi.display.height,
            Shared.scaledDisplay.width,
            Shared.scaledDisplay.height
        ) {
            mainWindow.renderWindow()
        }
    }
    
    fun onKeyDown(keyCode: Int): Boolean {
//        if (keyCode == Keyboard.KEY_TAB)
//            displayMainWindow.value = !displayMainWindow.value
        
        if (!displayMainWindow.value)
            return false
        
//        if (keyCode == Keyboard.KEY_ESCAPE)
//            displayMainWindow.value = false
        
        mainWindow.onKeyDown(keyCode)
        return true
    }
    
    fun onKeyUp(keyCode: Int) {
        mainWindow.onKeyUp(keyCode)
    }
    
    fun onMouseDown(buttonCode: Int): Boolean {
        if (!displayMainWindow.value)
            return false
        
        mainWindow.onMouseDown(buttonCode)
        return true
    }
    
    fun onMouseUp(buttonCode: Int) {
        mainWindow.onMouseUp(buttonCode)
    }
    
}