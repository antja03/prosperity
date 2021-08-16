package net.melonclient.minecraft189.impl.lwjgl.opengl

import net.melonclient.minecraftapi.api.lwjgl.opengl.IntermediateOpenGL
import org.lwjgl.opengl.GL11.*
import org.lwjgl.opengl.GL12.GL_CLAMP_TO_EDGE
import java.nio.ByteBuffer

class IntermediateOpenGL : IntermediateOpenGL {
    override val quads: Int = GL_QUADS
    override val lineStrip: Int = GL_LINE_STRIP
    override val triangleFan: Int = GL_TRIANGLE_FAN
    override val lines: Int = GL_LINES
    override val triangles: Int = GL_TRIANGLES
    
    override fun lineSmooth(init: () -> Unit) {
        glEnable(GL_LINE_SMOOTH)
        glHint(GL_LINE_SMOOTH_HINT, GL_NICEST)
        init()
        glDisable(GL_LINE_SMOOTH)
    }
    
    override fun lineWidth(width: Float) {
        glLineWidth(width)
    }
    
    override fun matrix(init: () -> Unit) {
        glPushMatrix()
        init()
        glPopMatrix()
    }
    
    override fun context2D(init: () -> Unit) {
        glDisable(GL_TEXTURE_2D)
        init()
        glEnable(GL_TEXTURE_2D)
    }
    
    override fun blend(init: () -> Unit) {
        glEnable(GL_BLEND)
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA)
        init()
        glDisable(GL_BLEND)
    }
    
    override fun color(r: Float, g: Float, b: Float, a: Float, init: () -> Unit) {
        glColor4f(r, g, b, a)
        init()
    }
    
    override fun drawInMode(mode: Int, init: () -> Unit) {
        glBegin(mode)
        init()
        glEnd()
    }
    
    override fun vertexWithTex2f(texX: Float, texY: Float, x: Float, y: Float) {
        glTexCoord2f(texX, texY)
        glVertex2f(x, y)
    }
    
    override fun vertex2f(x: Float, y: Float) {
        glVertex2f(x, y)
    }
    
    override fun texture(id: Int, init: () -> Unit) {
        val oldTex = glGetInteger(GL_TEXTURE_BINDING_2D)
        glBindTexture(GL_TEXTURE_2D, id)
        init()
        glBindTexture(GL_TEXTURE_2D, oldTex)
    }
    
    override fun translate(x: Float, y: Float, z: Float) {
        glTranslatef(x, y, z)
    }
    
    override fun rotate(angle: Float, x: Float, y: Float, z: Float) {
        glRotatef(angle, x, y, z)
    }
    
    override fun setupOrtho(width: Float, height: Float) {
        glClear(GL_DEPTH_BUFFER_BIT)
        glMatrixMode(GL_PROJECTION)
        glLoadIdentity()
        glOrtho(0.0, width.toDouble(), height.toDouble(), 0.0, 1000.0, 3000.0)
        glMatrixMode(GL_MODELVIEW)
        glLoadIdentity()
        glTranslatef(0.0f, 0.0f, -2000.0f)
    }
    
    override fun genTextures() = glGenTextures()
    
    override fun uploadTexture(id: Int, width: Int, height: Int, texture: ByteBuffer) {
        glBindTexture(GL_TEXTURE_2D, id)
        glPixelStorei(GL_UNPACK_ALIGNMENT, 1)
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_CLAMP_TO_EDGE)
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP_TO_EDGE)
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR)
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR)
        glTexImage2D(
            GL_TEXTURE_2D,
            0,
            GL_RGBA8,
            width,
            height,
            0,
            GL_RGBA,
            GL_UNSIGNED_BYTE,
            texture
        )
        glBindTexture(GL_TEXTURE_2D, 0)
    }
}