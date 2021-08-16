package net.melonclient.client.theming

object ThemeManager {

    val themeables = hashMapOf<String, Themeable>()
    val themes = hashMapOf<String, Theme>()

    fun registerThemeable(identifier: String): Themeable {
        if (themeables.containsKey(identifier))
            return themeables[identifier]!!

        val themeable = Themeable(identifier)
        themeables[identifier] = themeable
        return themeable
    }


}