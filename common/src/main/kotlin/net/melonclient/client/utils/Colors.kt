package net.melonclient.client.utils


object Colors {

    private const val shadowDarkener = 0.3f
    private const val shadowAlpha = 0.8f

    fun getRgb(red: Int, green: Int, blue: Int): Int {
        return getRgba(red, green, blue, 255)
    }

    fun getRgba(red: Int, green: Int, blue: Int, alpha: Int): Int {
        return (alpha and 255 shl 24) or
                (red and 255 shl 16) or
                (green and 255 shl 8) or
                (blue and 255 shl 0)
    }

    fun modifyColorAlpha(color: Int, alpha: Int): Int = getRgba(
                (color shr 16 and 255),
                (color shr 8 and 255),
                (color and 255),
                alpha)

    fun getMultipliedColor(color: Int, multiplier: Float, includeAlpha : Boolean = false): Int {
        val multipliedRgba = getMultipliedRgbaI(color, multiplier, includeAlpha)
        return getRgba(multipliedRgba[0], multipliedRgba[1], multipliedRgba[2], multipliedRgba[3])
    }

    fun getMultipliedRgbaI(color: Int, multiplier: Float, includeAlpha : Boolean = false): IntArray {
        val colorArray = IntArray(4)
        colorArray[0] = ((color shr 16 and 255) * multiplier).toInt()
        colorArray[1] = ((color shr 8 and 255) * multiplier).toInt()
        colorArray[2] = ((color and 255) * multiplier).toInt()
        colorArray[3] = ((color shr 24 and 255) * if (includeAlpha) multiplier else 1.0f).toInt()
        return validateRgbaI(colorArray)
    }

    fun getMultipliedRgbaF(color: Int, multiplier: Float, includeAlpha : Boolean = false): FloatArray {
        val colorArray = FloatArray(4)
        colorArray[0] = (color shr 16 and 255).toFloat() / (255.0f / if (includeAlpha) multiplier else 1.0f)
        colorArray[1] = (color shr 8 and 255).toFloat() / (255.0f / if (includeAlpha) multiplier else 1.0f)
        colorArray[2] = (color and 255).toFloat() / (255.0f / if (includeAlpha) multiplier else 1.0f)
        colorArray[3] = (color shr 24 and 255).toFloat() / (255.0f / if (includeAlpha) multiplier else 1.0f)
        return validateRgbaF(colorArray)
    }

    fun getShadowColor(color: Int): Int {
        val shadowRgba = getShadowRgbaI(color)
        return (shadowRgba[3] and 255 shl 24) or
                (shadowRgba[2] and 255 shl 16) or
                (shadowRgba[1] and 255 shl 8) or
                (shadowRgba[0] and 255 shl 0)
    }

    fun getShadowRgbaI(color: Int): IntArray {
        val colorArray = IntArray(4)
        colorArray[0] = ((color shr 16 and 255) * shadowDarkener).toInt()
        colorArray[1] = ((color shr 8 and 255) * shadowDarkener).toInt()
        colorArray[2] = ((color and 255) * shadowDarkener).toInt()
        colorArray[3] = (255 * shadowAlpha).toInt()
        return validateRgbaI(colorArray)
    }

    fun getShadowRgbaF(color: Int): FloatArray {
        val colorArray = FloatArray(4)
        colorArray[0] = (color shr 16 and 255).toFloat() / (255.0f * (1f / shadowDarkener))
        colorArray[1] = (color shr 8 and 255).toFloat() / (255.0f * (1f / shadowDarkener))
        colorArray[2] = (color and 255).toFloat() / (255.0f * (1f / shadowDarkener))
        colorArray[3] = shadowAlpha
        return validateRgbaF(colorArray)
    }

    fun validateRgbaI(rgba: IntArray): IntArray {
        val rgba = rgba
        for (i in rgba.indices) {
            rgba[i] = validateColorI(rgba[i])
        }
        return rgba
    }

    fun validateRgbaF(rgba: FloatArray): FloatArray {
        val rgba = rgba
        for (i in rgba.indices) {
            rgba[i] = validateColorF(rgba[i])
        }
        return rgba
    }

    fun validateColorI(color: Int): Int {
        val color = color

        if (color > 255)
            return 255

        if (color < 0)
            return 0

        return color
    }

    fun validateColorF(color: Float): Float {
        val color = color

        if (color > 1.0f)
            return 1.0f

        if (color < 0.0f)
            return 0.0f

        return color
    }

}