package net.melonclient.client.data.setting

import net.melonclient.client.data.reference.MutableReference

open class Setting<T>(val id: String, value: T): MutableReference<T>(value) {
    
    lateinit var displayName: String
    lateinit var description: String

}