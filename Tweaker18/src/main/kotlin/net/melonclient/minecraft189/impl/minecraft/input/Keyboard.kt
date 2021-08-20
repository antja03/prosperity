package net.melonclient.minecraft189.impl.minecraft.input

import net.melonclient.minecraftapi.api.minecraft.input.Keyboard
import java.lang.reflect.Modifier

object Keyboard : Keyboard {
    override val keyMap: MutableMap<String, Int> = mutableMapOf()
    
    init {
        org.lwjgl.input.Keyboard::class.java.declaredFields.forEach {
            if((Modifier.isStatic(it.modifiers)
                        && Modifier.isPublic(it.modifiers)
                        && Modifier.isFinal(it.modifiers)
                        && it.type == Int::class.javaPrimitiveType && it.name.startsWith("KEY_")
                        && !it.name.endsWith("WIN"))) {
                keyMap[it.name.substring(4)] = it.get(null) as Int
            }
        }
    }
    
    override fun getKeyHeld(key: Int): Boolean {
        return org.lwjgl.input.Keyboard.isKeyDown(key)
    }
    
    override fun enableRepeatEvents(enable: Boolean) {
        org.lwjgl.input.Keyboard.enableRepeatEvents(enable)
    }
}