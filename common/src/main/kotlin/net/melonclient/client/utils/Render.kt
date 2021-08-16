package net.melonclient.client.utils


import net.melonclient.client.Client
import kotlin.math.cos
import kotlin.math.sin

object Render {
    
    val intermediate = Client.lwjglApi.intermediateOpenGL
    
    fun drawRect(x: Float, y: Float, w: Float, h: Float, color: Int) {
        with(intermediate) {
            color(color) {
                drawInMode(quads) {
                    vertex2f(x, y)
                    vertex2f(x, y + h)
                    vertex2f(x + w, y + h)
                    vertex2f(x + w, y)
                }
            }
        }
    }
    
    fun drawRect(x: Double, y: Double, w: Double, h: Double, color: Int) {
        drawRect(x.toFloat(), y.toFloat(), w.toFloat(), h.toFloat(), color)
    }
    
    fun drawRect(x: Int, y: Int, w: Int, h: Int, color: Int) {
        drawRect(x.toFloat(), y.toFloat(), w.toFloat(), h.toFloat(), color)
    }
    
    fun drawArc(x: Int, y: Int, width: Int, radius: Float, rotation: Int, color: Int) =
        with(intermediate) {
            intermediate.lineWidth(1f)
            intermediate.lineSmooth {
                color(color) {
                    drawInMode(triangleFan) {
                        val parts = (radius * 2).toInt()
                        val f1 = 90f / parts
                        val f2: Float = x + radius
                        val f3: Float = y + radius
                        vertex2f(f2, f3)
                        for (j in 0..parts) {
                            val f4 = j * f1 + rotation
                            vertex2f(
                                (f2 + radius * cos(Math.toRadians(f4.toDouble()))).toFloat(),
                                (f3 - radius * sin(Math.toRadians(f4.toDouble()))).toFloat()
                            )
                        }
                    }
                }
            }
        }
    
    
    fun drawArcOutline(x: Int, y: Int, radius: Float, rotation: Int, width: Float, color: Int) {
        with(intermediate) {
            setup2D {
                color(color) {
                    lineWidth(0.5f)
                    drawInMode(lineStrip) {
                        val parts = (radius * 200).toInt()
                        val f1 = 90f / parts
                        val f2: Float = x + radius
                        val f3: Float = y + radius
                        for (j in 0..parts) {
                            val f4 = j * f1 + rotation
                            vertex2f(
                                (f2 + radius * cos(Math.toRadians(f4.toDouble()))).toFloat(),
                                (f3 - radius * sin(Math.toRadians(f4.toDouble()))).toFloat()
                            )
                        }
                    }
                }
            }
        }
    }
    
    fun drawRoundRect(x: Int, y: Int, width: Int, height: Int, radius: Float, color: Int) {
        val borderSize = radius.toInt()
        val cornerX = x + borderSize
        val cornerY = y + borderSize
        val cornerW = width - borderSize * 2
        val cornerH = height - borderSize * 2
        
        drawRect(cornerX, cornerY - borderSize, cornerW, borderSize, color)
        drawRect(cornerX - borderSize, cornerY, borderSize, cornerH, color)
        drawRect(cornerX, cornerY + cornerH, cornerW, borderSize, color)
        drawRect(cornerX + cornerW, cornerY, borderSize, cornerH, color)
        drawRect(cornerX, cornerY, cornerW, cornerH, color)
        
        drawArc(x, y, radius.toInt() / 2, radius, 90, color)
        drawArc(x + cornerW, y, radius.toInt() / 2, radius, 0, color)
        drawArc(x, y + cornerH, radius.toInt() / 2, radius, 180, color)
        drawArc(x + cornerW, y + cornerH, radius.toInt() / 2, radius, 270, color)
    }
    
    fun drawRoundRectOutline(x: Int, y: Int, width: Int, height: Int, lineWidth: Int, radius: Float, color: Int) {
        val borderSize = radius.toInt()
        val cornerX = x + borderSize
        val cornerY = y + borderSize
        val cornerW = width - borderSize * 2
        val cornerH = height - borderSize * 2
        
        drawRect(cornerX, y - lineWidth, cornerW, lineWidth, color)
        drawRect(x - lineWidth, cornerY, lineWidth, cornerH, color)
        drawRect(cornerX, y + height, cornerW, lineWidth, color)
        drawRect(x + width, cornerY, lineWidth, cornerH, color)
//
        drawArcOutline(x, y, radius, 90, lineWidth.toFloat(), color)
        drawArcOutline(x + cornerW, y, radius, 0, lineWidth.toFloat(), color)
        drawArcOutline(x, y + cornerH, radius, 180, lineWidth.toFloat(), color)
        drawArcOutline(x + cornerW, y + cornerH, radius, 270, lineWidth.toFloat(), color)
    }
    
    fun drawImg(id: Int, posX: Int, posY: Int, width: Int, height: Int, angle: Float = 0f, color: Int = -1) {
        with(intermediate) {
            setup2D {
                color(color) {
                    texture(id) {
                        translate(posX + width / 2f, posY + height / 2f, 0f)
                        rotate(angle, 0f, 0f, 1f)
                        translate(-width / 2f, -height / 2f, 0f)
                        drawInMode(quads) {
                            vertexWithTex2f(0f, height.toFloat(), 0f, height.toFloat())
                            vertexWithTex2f(width.toFloat(), height.toFloat(), width.toFloat(), height.toFloat())
                            vertexWithTex2f(width.toFloat(), 0f, width.toFloat(), 0f)
                            vertexWithTex2f(width.toFloat(), height.toFloat(), width.toFloat(), height.toFloat())
                        }
                    }
                }
            }
        }
    }
}


fun color(color: Int, init: () -> Unit) {
    Client.lwjglApi.intermediateOpenGL.color(
        (color shr 16 and 255).toFloat() / 255.0f,
        (color shr 8 and 255).toFloat() / 255.0f,
        (color and 255).toFloat() / 255.0f,
        (color shr 24 and 255).toFloat() / 255.0f, init
    )
    init()
}

