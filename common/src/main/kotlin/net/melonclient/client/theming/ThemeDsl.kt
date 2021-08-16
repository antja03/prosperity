package net.melonclient.client.theming

fun registerThemeable(identifier: String, init: Themeable.() -> Unit): Themeable {
    val themeable = ThemeManager.registerThemeable(identifier)
    themeable.init()
    return themeable
}

fun Themeable.addProperty(identifier: String, value: Any) {
    this.addProperty(identifier, value)
}

fun usingThemeable(themeable: Themeable, run: Themeable.() -> Unit) {
    themeable.run()
}