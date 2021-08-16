package net.melonclient.client.theming

interface ThemeOwner {
    
    val themeable: Themeable
    
    fun setDefaultTheme()
    
}