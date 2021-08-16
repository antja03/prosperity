package net.melonclient.client.theming

import net.melonclient.client.utils.PrimitiveParser

class Themeable(val identifier: String) {
    private val propertyMap = hashMapOf<String, String>()
    private val callbackList = arrayListOf<(String) -> Unit>()

    fun addProperty(identifier: String, value: Any) {
        propertyMap[identifier] = value.toString()
    }

    fun addCallback(callback: (String) -> Unit) {
        callbackList.add(callback)
    }

    fun getStringValue(identifier: String): String? {
        if (propertyMap.containsKey(identifier))
            return propertyMap[identifier]!!
        return null
    }

    fun setStringValue(identifier: String, value: String) {
        propertyMap[identifier] = value

        callbackList.forEach { callback ->
            callback.invoke(identifier)
        }
    }

    fun getBooleanValue(identifier: String): Boolean? {
        if (propertyMap.containsKey(identifier))
            PrimitiveParser.stringToBoolean(propertyMap[identifier]!!)
        return null
    }

    fun setBooleanValue(identifier: String, value: Boolean) {
        if (!propertyMap.containsKey(identifier))
            addProperty(identifier, value.toString())
        else
            setStringValue(identifier, value.toString())
    }

    fun getIntValue(identifier: String): Int? {
        if (propertyMap.containsKey(identifier))
            return PrimitiveParser.stringToInt(propertyMap[identifier]!!)
        return null
    }

    fun setIntValue(identifier: String, value: Int) {
        if (!propertyMap.containsKey(identifier))
            addProperty(identifier, value.toString())
        else
            setStringValue(identifier, value.toString())
    }

    fun getFloatValue(identifier: String): Float? {
        if (propertyMap.containsKey(identifier))
            return PrimitiveParser.stringToFloat(propertyMap[identifier]!!)
        
        return null
    }

    fun setFloatValue(identifier: String, value: Float) {
        if (!propertyMap.containsKey(identifier))
            addProperty(identifier, value.toString())
        else
            setStringValue(identifier, value.toString())
    }
}