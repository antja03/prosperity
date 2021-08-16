package net.melonclient.minecraftapi.api.minecraft.font

interface TextRenderer {
    fun draw(text: String, x: Float, y: Float, color: Int)
    fun drawCentered(text: String, x: Float, y: Float, color: Int)
    fun drawWithShadow(text: String, x: Float, y: Float, color: Int)
    fun drawCenteredWithShadow(text: String, x: Float, y: Float, color: Int)
    fun getCharacterWidth(character: Char, fontType: Int): Float
    fun getCharacterHeight(character: Char, fontType: Int): Float
    fun getStringWidth(text: String): Float
    fun getStringHeight(text: String): Float
}