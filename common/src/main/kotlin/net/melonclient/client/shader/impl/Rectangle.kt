package net.melonclient.client.shader.impl

//import net.melonclient.client.shader.Shader
//import org.lwjgl.util.vector.Vector4f
//import org.lwjgl.opengl.GL11.*
//import org.lwjgl.opengl.GL14
//
//object Rectangle : Shader("rectangle") {
//
//    fun render(rect: Vector4f, color: Vector4f, radius: Float = 1f) {
//        var radius = radius * 8
//        setFloat("radius", radius)
//        setVector4f("innerRect", Vector4f(rect.x + radius, rect.y + radius, rect.x + rect.z - radius, rect.y + rect.w - radius))
//        glColor4f(color.x, color.y, color.z, color.w)
//        glBegin(GL_QUADS)
//        glVertex2f(rect.x, rect.y)
//        glVertex2f(rect.x, rect.y + rect.w)
//        glVertex2f(rect.x + rect.z, rect.y + rect.w)
//        glVertex2f(rect.x + rect.z, rect.y)
//        glEnd()
//
//    }
//    override fun start() {
//        glPushMatrix()
//
//        glDisable(GL_TEXTURE_2D)
//        glEnable(GL_BLEND)
//        glBlendFunc(770, 771)
//        super.start()
//    }
//    override fun stop() {
//
//        super.stop()
//        glEnable(GL_TEXTURE_2D)
//        glPopMatrix()
//    }
//    enum class RectangleType {
//        ROUND, FRAME
//
//    }
//
//}
//
//fun rectangles(init: Rectangle.() -> Unit) {
//
//    Rectangle.start()
//    init.invoke(Rectangle)
//    Rectangle.stop()
//}