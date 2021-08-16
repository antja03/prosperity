package net.melonclient.minecraftapi.api.lwjgl.opengl

import java.nio.ByteBuffer

interface IntermediateOpenGL {
    
    val quads: Int
    
    val lineStrip: Int
    
    val triangleFan: Int
    
    val lines: Int
    
    val triangles: Int
    
    fun lineSmooth(init: () -> Unit)
    
    fun lineWidth(width: Float)
    
    fun matrix(init: () -> Unit)
    
    fun context2D(init: () -> Unit)
    
    fun blend(init: () -> Unit)
    
    fun color(r: Float, g: Float, b: Float, a: Float, init: () -> Unit)
    
    fun drawInMode(mode: Int, init: () -> Unit)
    
    fun vertexWithTex2f(texX: Float, texY: Float, x: Float, y: Float)
    
    fun vertex2f(x: Float, y: Float)
    
    fun texture(id: Int, init: () -> Unit)
    
    fun translate(x: Float, y: Float, z: Float)
    
    fun rotate(angle: Float, x: Float, y: Float, z: Float)
    
    fun setup2D(init: () -> Unit) {
        matrix {
            context2D {
                blend {
                    init()
                }
            }
        }
    }
    
    fun setupOrtho(width: Float, height: Float)
    
    fun genTextures(): Int
    
    fun uploadTexture(id: Int, width: Int, height: Int, texture: ByteBuffer)
}