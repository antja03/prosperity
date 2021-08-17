package net.melonclient.client.utils

import net.melonclient.client.Client
import net.melonclient.minecraftapi.api.minecraft.font.TextRenderer

import java.awt.Color
import java.awt.Font
import java.awt.RenderingHints
import java.awt.font.TextAttribute
import java.awt.image.BufferedImage
import java.nio.ByteBuffer
import java.util.*
import kotlin.math.abs


/**
 * Anthony A.
 * 7/31/2020
 * 9:07 PM
 **/
data class FontData(
    val font: Font,
    val characterData: FloatArray,
    val characterStart: Int,
    val characterEnd: Int,
    val fontImage: BufferedImage,
    val textureSizeW: Int,
    val textureSizeH: Int
)

class CharacterDataGenerator(val font: Font, val characterStart: Int, val characterEnd: Int) {
    private fun drawCharacter(character: Char, width: Int, height: Int): BufferedImage? {
        val bufferedImage = BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB)
        val graphics2D = bufferedImage.createGraphics()
        graphics2D.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON)
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)
        graphics2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON)
        graphics2D.font = font
        graphics2D.background = Color(0, 0, 0, 0)
        graphics2D.color = Color.WHITE
        val fontMetrics = graphics2D.fontMetrics
        val text = character.toString()
        graphics2D.clearRect(0, 0, width, height)
        val stringBounds = fontMetrics.getStringBounds(text, graphics2D).bounds
        graphics2D.drawString(text, 0f, abs(stringBounds.y) - font.size / 3f)
        val w = stringBounds.width
        if (w <= 0) {
            return null
        }
        val subImage = bufferedImage.getSubimage(0, 0, w, abs(stringBounds.y))
        if (character == 'Y' && font.isItalic && font.isBold) {
            println("test")
        }
        graphics2D.dispose()
        return subImage
    }
    
    fun generateCharacters(): Array<BufferedImage?>? {
        val characterDataArray = arrayOfNulls<BufferedImage>(characterEnd - characterStart)
        for (character in characterStart until characterEnd) {
            val bufferedImage = drawCharacter(character.toChar(), font.size * 4, font.size * 4)
                ?: continue
            characterDataArray[character - characterStart] = bufferedImage
        }
        return characterDataArray
    }
}

class CharacterDataMerger(val characterDataGenerators: List<CharacterDataGenerator>, val font: Font) {
    fun merge() = when (characterDataGenerators.size) {
        0 -> {
            throw RuntimeException("provided with 0 character data generators")
        }
        1 -> {
            merge(characterDataGenerators[0].generateCharacters())
        }
        else -> {
            val characterDatas = arrayListOf<Array<BufferedImage?>?>()
            characterDataGenerators.forEach {
                characterDatas += it.generateCharacters()
            }
            merge(join(characterDatas))
        }
    }
    
    private fun join(list: List<Array<BufferedImage?>?>): Array<BufferedImage?>? {
        var length = 0
        for (i in list.indices) {
            length += list[i]!!.size
        }
        val newArray = arrayOfNulls<BufferedImage>(length)
        for (i in list.indices) {
            val array = list[i]
            for (i1 in array!!.indices) {
                newArray[i * array.size + i1] = array[i1]
            }
        }
        return newArray
    }
    
    private fun merge(characterDataList: Array<BufferedImage?>?): FontData? {
        return mergeInternal(characterDataList, this.getTextureSize(characterDataList, (font.size).toInt()))
    }
    
    /**
     * Merges all of the characters into one big Buffered Image
     *
     * @param characterDataList the characters to merge together
     * @return merged buffered image
     */
    private fun mergeInternal(characterDataList: Array<BufferedImage?>?, textureSize: Pair<Int, Int>): FontData? {
        if (characterDataList != null) {
            val bufferedImage = BufferedImage(textureSize.first, textureSize.second, BufferedImage.TYPE_INT_ARGB)
            var x = 0
            val characterTexLocations = FloatArray(characterDataList.size * STRUCT_SIZE)
            var y: Int = 0
            for (i in characterDataList.indices) {
                val characterDataBufferedImage = characterDataList[i]!!
                val fontMetrics = characterDataList[0]!!.graphics.fontMetrics
                
                if (x + characterDataBufferedImage.width > textureSize.first) {
                    
                    y += fontMetrics.height + font.size / 3
                    x = 0
                }
                bufferedImage.graphics.drawImage(characterDataBufferedImage, x, y, null)
                characterTexLocations[i * STRUCT_SIZE] = x / textureSize.first.toFloat()
                characterTexLocations[i * STRUCT_SIZE + 1] = y / textureSize.second.toFloat()
                characterTexLocations[i * STRUCT_SIZE + 2] =
                    characterDataBufferedImage.width / textureSize.first.toFloat()
                characterTexLocations[i * STRUCT_SIZE + 3] =
                    (characterDataBufferedImage.height) / textureSize.second.toFloat()
                x += characterDataBufferedImage.width
            }
            return FontData(
                this.font,
                characterTexLocations,
                characterDataGenerators[0].characterStart,
                characterDataGenerators[0].characterEnd,
                bufferedImage,
                textureSize.first,
                textureSize.second
            )
        }
        return null
    }
    
    //    /**
//     * @param textures A list of approximately-square BufferedImages (wrapped in a
//     * CharacterInfo).
//     * @return The minimum square texture size, in pixels, needed to fit all of the
//     * given textures - rounded up to the nearest power of two.
//     * @author Henry
//     *
//     *
//     * Calculates and returns the required texture/atlas size that is capable of
//     * fitting all of the passed textures. This method assumes the following: that
//     * the final texture will be square, that the passed textures are approximately
//     * square, and that there is no margin of error for the bin-packing algorithm.
//     */
//    private fun getTextureSize(textures: Array<BufferedImage?>?): Int {
//        var glyphArea = 0.0
//        if (textures != null) {
//            textures.forEach {
//                if (it != null)
//                    glyphArea += (it.width * it.height)
//            }
//            val sqrtArea = ceil(sqrt(glyphArea)).roundToInt()
//            val log2 = ceil(ln(sqrtArea.toDouble()) / ln(2.0)).roundToInt()
//            return 2.0.pow(log2.toDouble()).roundToInt()
//        }
//        return -1
//    }
    /**
     * Returns an exact amount of width/height required to draw a group of textures
     */
    private fun getTextureSize(textures: Array<BufferedImage?>?, groupsOf: Int): Pair<Int, Int> {
        var width = 0
        var height = 0
        textures!!.forEach {
            if (it!!.width > width) {
                width = it.width
            }
            if (it.height > height) {
                height = it.height
            }
        }
        return Pair(width * groupsOf, height * groupsOf)
    }
    
    companion object {
        const val STRUCT_SIZE = 4
    }
}

class CharacterInfoMapper(private var font: Font) {
    fun map(): FontData? {
        font = font.deriveFont(font.size * 2f)
        val fontStyles = arrayOf(
            font,
            font.deriveFont(Font.BOLD), font.deriveFont(Font.ITALIC), font.deriveFont(Font.BOLD or Font.ITALIC)
        )
        val characterDataGenerators = arrayListOf<CharacterDataGenerator>()
        for (font in fontStyles) {
            characterDataGenerators += CharacterDataGenerator(font, 32, 127)
        }
        return CharacterDataMerger(characterDataGenerators, font).merge()
    }
}


class CustomTextRenderer(private val fontData: FontData) : TextRenderer {
    private val textureID: Int = Client.lwjglApi.intermediateOpenGL.genTextures()
    
    init {
        Client.lwjglApi.intermediateOpenGL.uploadTexture(
            textureID,
            fontData.textureSizeW,
            fontData.textureSizeH,
            getByteBufferFromBufferedImage(fontData.fontImage)
        )
//        glBindTexture(GL_TEXTURE_2D, textureID)
//        glPixelStorei(GL_UNPACK_ALIGNMENT, 1)
//        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_CLAMP_TO_EDGE)
//        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP_TO_EDGE)
//        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR)
//        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR)
//        glTexImage2D(
//            GL_TEXTURE_2D,
//            0,
//            GL_RGBA8,
//            fontData.textureSizeW,
//            fontData.textureSizeH,
//            0,
//            GL_RGBA,
//            GL_UNSIGNED_BYTE,
//            getByteBufferFromBufferedImage(fontData.fontImage)
//        )
//        glBindTexture(GL_TEXTURE_2D, 0)
    }
    
    override fun draw(text: String, x: Float, y: Float, color: Int) {
        drawInternal(text, x, y, color)
    }
    
    override fun drawCentered(text: String, x: Float, y: Float, color: Int) {
        drawInternal(text, x - getStringWidth(text) / 2, y - getStringHeight(text) / 2, color)
    }
    
    override fun drawWithShadow(text: String, x: Float, y: Float, color: Int) {
        drawInternal(text, x, y, color, true)
    }
    
    override fun drawCenteredWithShadow(text: String, x: Float, y: Float, color: Int) {
        drawInternal(text, x - getStringWidth(text) / 2, y - getStringHeight(text) / 2, color, true)
    }
    
    private fun drawCharacter(
        x: Float,
        y: Float,
        texX: Float,
        texY: Float,
        texWidth: Float,
        texHeight: Float,
        color: Int,
        shadow: Boolean
    ) {
        val x = x / 2
        val y = y / 2
        
        if (shadow) {
            val shadowColors = Colors.getShadowRgbaF(color)
            Client.lwjglApi.intermediateOpenGL.color(
                shadowColors[0],
                shadowColors[1],
                shadowColors[2],
                shadowColors[3]
            ) {}
            drawCharacter(x - 1, y - 1, texX, texY, texWidth, texHeight, color, false)
            glColor4f(color, (color shr 24 and 255) / 255f)
        }
        with(Client.lwjglApi.intermediateOpenGL) {
            drawInMode(triangles) {
                vertexWithTex2f(texX + texWidth, texY, x + texWidth * fontData.textureSizeW, y)
                vertexWithTex2f(texX, texY, x, y)
                vertexWithTex2f(texX, texY + texHeight, x, y + texHeight * fontData.textureSizeH)
                vertexWithTex2f(texX, texY + texHeight, x, y + texHeight * fontData.textureSizeH)
                vertexWithTex2f(
                    texX + texWidth,
                    texY + texHeight,
                    x + texWidth * fontData.textureSizeW,
                    y + texHeight * fontData.textureSizeH
                )
                vertexWithTex2f(texX + texWidth, texY, x + texWidth * fontData.textureSizeW, y)
            }
        }
        
    }
    
    @Suppress("NAME_SHADOWING")
    private fun drawInternal(text: String, x: Float, y: Float, color: Int, shadow: Boolean = false) {
        var x = x * 2
        val y = y * 2
        
        val characterArray = text.toCharArray()
        var fontStructIndex = 0
        var actualColor = color
        
        with(Client.lwjglApi.intermediateOpenGL) {
            setup2D {
                
                var flag = 0
                var index = 0
                while (index < characterArray.size) {
                    val character = characterArray[index]
                    // Minecraft formatting
                    if ((character == '\u00A7' || character == '&') && index + 1 < characterArray.size) {
                        when (val char = characterArray[index + 1]) {
                            'l' -> {
                                fontStructIndex = if (fontStructIndex == 2) 3 else 1
                            }
                            'o' -> {
                                fontStructIndex = if (fontStructIndex == 1) 3 else 2
                            }
                            'r' -> {
                                fontStructIndex = 0
                                flag = 0
                                actualColor = 0
                            }
                            'm' -> {
                                flag = if (flag == 2) 3 else 1
                            }
                            'n' -> {
                                flag = if (flag == 1) 3 else 2
                            }
                            else -> {
//                                actualColor = minecraft.fontRendererObj.getColorCode(char)
                            }
                        }
                        index += 2
                        continue
                    }
                    
                    val charIndex = character.code - fontData.characterStart
                    val totalChars = fontData.characterEnd - fontData.characterStart
                    val characterLocation =
                        (CharacterDataMerger.STRUCT_SIZE * charIndex) + (CharacterDataMerger.STRUCT_SIZE * (totalChars * (fontStructIndex)))
                    if (characterLocation + 3 <= fontData.characterData.size) {
                        glColor4f(actualColor, (actualColor shr 24 and 255) / 255f)
                        drawCharacter(
                            x,
                            y,
                            fontData.characterData[characterLocation],
                            fontData.characterData[characterLocation + 1],
                            fontData.characterData[characterLocation + 2],
                            fontData.characterData[characterLocation + 3],
                            actualColor,
                            shadow
                        )
                        if (flag != 0) {
                            val width = getCharacterWidth(character, fontStructIndex) / 2
                            
                            if (flag == 1 || flag == 3) {
                                val y = y / 2 + getStringHeight(text) + 3
                                drawInMode(lines) {
                                    vertex2f(x / 2, y)
                                    vertex2f(x / 2 + width, y)
                                }
                            }
                            
                            if (flag == 2 || flag == 3) {
                                val y = y / 2 + getStringHeight(text) / 2
                                
                                drawInMode(lines) {
                                    vertex2f(x / 2, y)
                                    vertex2f(x / 2 + width, y)
                                }
                            }
                        }
                    }
                    index++
                    x += getCharacterWidth(character, fontStructIndex)
                    
                }
            }
        }
    }
    
    override fun getStringWidth(text: String): Float {
        var width = 0f
        var index = 0
        val characterArray = text.toCharArray()
        var fontStructIndex = 0
        
        while (index < characterArray.size) {
            val character = characterArray[index]
            if (character == '\u00A7' && index + 1 < characterArray.size) {
                when (characterArray[index + 1]) {
                    'l' -> {
                        fontStructIndex = if (fontStructIndex == 2) 3 else 1
                    }
                    'o' -> {
                        fontStructIndex = if (fontStructIndex == 1) 3 else 2
                    }
                    'r' -> {
                        fontStructIndex = 0
                    }
                }
                index += 2
                continue
            }
            width += getCharacterWidth(character, fontStructIndex)
            index++
        }
        return width
    }
    
    override fun getCharacterHeight(character: Char, fontType: Int): Float {
        val searchingIndex = CharacterDataMerger.STRUCT_SIZE * (character.toInt() - fontData.characterStart)
        
        if (searchingIndex + 3 >= fontData.characterData.size)
            return 0.0f
        
        return (fontData.characterData[searchingIndex + 3] * fontData.textureSizeH) - fontData.font.size / 3
    }
    
    override fun getCharacterWidth(character: Char, fontType: Int): Float {
        val searchingIndex = CharacterDataMerger.STRUCT_SIZE * (character.code - fontData.characterStart)
        
        if (searchingIndex + 2 >= fontData.characterData.size)
            return 0.0f
        
        return (fontData.characterData[searchingIndex + 2] * fontData.textureSizeW) * 2
    }
    
    override fun getStringHeight(text: String): Float {
        var maxHeight = Float.MIN_VALUE
        val characterArray = text.toCharArray()
        var index = 0
        var fontStructIndex = 0
        
        while (index < text.toCharArray().size) {
            val character = text.toCharArray()[index]
            if (character == '\u00A7' && index + 1 < characterArray.size) {
                when (characterArray[index + 1]) {
                    'l' -> {
                        fontStructIndex = if (fontStructIndex == 2) 3 else 1
                    }
                    'o' -> {
                        fontStructIndex = if (fontStructIndex == 1) 3 else 2
                    }
                    'r' -> {
                        fontStructIndex = 0
                    }
                }
                index += 2
                continue
            }
            val charHeight = getCharacterHeight(character, fontStructIndex)
            if (maxHeight < charHeight)
                maxHeight = charHeight
            
            index++
        }
        
        return maxHeight
    }
    
    private fun glColor4f(color: Int, alpha: Float) {
        Client.lwjglApi.intermediateOpenGL.color(
            (color shr 16 and 255).toFloat() / 255f,
            (color shr 8 and 255).toFloat() / 255f,
            (color and 255).toFloat() / 255f,
            alpha
        ) {}
    }
    
    private fun getByteBufferFromBufferedImage(bufferedImage: BufferedImage): ByteBuffer {
        val pixels = IntArray(bufferedImage.width * bufferedImage.height)
        bufferedImage.getRGB(0, 0, bufferedImage.width, bufferedImage.height, pixels, 0, bufferedImage.width)
        val imageBuffer = Client.lwjglApi.buffer.createByteBuffer(4 * bufferedImage.width * bufferedImage.height)
        for (y in bufferedImage.height until 0) {
            for (x in bufferedImage.width until 0) {
                val pixel = pixels[y * bufferedImage.width + x]
                imageBuffer.put((pixel shr 24 and 0xFF).toByte())
                imageBuffer.put((pixel and 0xFF).toByte()) // Blue component
                imageBuffer.put((pixel shr 8 and 0xFF).toByte()) // Green component
                imageBuffer.put((pixel shr 16 and 0xFF).toByte()) // Red component
            }
        }
        
        return imageBuffer
    }
}

object CustomFontManager {
    private val renderers = hashMapOf<String, CustomTextRenderer>()
    
    fun get(
        fontName: String,
        fontSize: Int,
        fontType: Int,
        fontWeight: Float = TextAttribute.WEIGHT_REGULAR
    ): CustomTextRenderer {
        val fontId = "$fontName-$fontSize-$fontType-$fontWeight"
        if (!renderers.containsKey(fontId)) {
            var font = Font(fontName, fontType, fontSize)
            font = font.deriveFont(Collections.singletonMap(TextAttribute.WEIGHT, fontWeight))
            
            val textRenderer = CustomTextRenderer(CharacterInfoMapper(font).map()!!)
            renderers[fontId] = textRenderer
            return textRenderer
        }
        return renderers[fontId]!!
    }
}