package net.melonclient.client.utils

import java.lang.NumberFormatException

object PrimitiveParser {
    
    private val booleanMap = hashMapOf(
        "true" to true,
        "false" to false,
        "on" to true,
        "off" to false,
        "yes" to true,
        "no" to false
    )
    
    fun stringToBoolean(string: String): Boolean? {
        return booleanMap[string.toLowerCase()]
    }
    
    fun stringToInt(string: String): Int? {
        return try {
            string.toInt()
        } catch (e: NumberFormatException) {
            null
        }
    }
    
    fun stringToFloat(string: String): Float? {
        return try {
            string.toFloat()
        } catch (e: NumberFormatException) {
            null
        }
    }
    
}