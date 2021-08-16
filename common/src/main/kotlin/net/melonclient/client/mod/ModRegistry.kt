package net.melonclient.client.mod

import net.melonclient.client.mod.impl.CpsDisplay
import net.melonclient.client.mod.impl.KeyStrokes
import net.melonclient.client.mod.impl.ToggleSprint

class ModRegistry: ArrayList<Mod>() {
    
    init {
        add(ToggleSprint())
        add(CpsDisplay())
        add(KeyStrokes())
    }
    
    fun getModByClass(clazz: Class<out Mod>): Mod? {
        for (mod in this)
            if (mod::class.java == clazz)
                return mod
        return null
    }
    
    fun getModById(id: String): Mod? {
        for (mod in this)
            if (mod.id == id)
                return mod
        return null
    }
}