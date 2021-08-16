package net.melonclient.client.shader
//
//import net.melonclient.client.Constants
//import org.apache.commons.io.FileUtils
//import org.lwjgl.BufferUtils
//import org.lwjgl.opengl.GL11.GL_FALSE
//import org.lwjgl.opengl.GL11.glGetInteger
//import org.lwjgl.opengl.GL20.*
//import org.lwjgl.util.vector.Matrix4f
//import org.lwjgl.util.vector.Vector2f
//import org.lwjgl.util.vector.Vector3f
//import org.lwjgl.util.vector.Vector4f
//import java.io.File
//import java.nio.FloatBuffer
//
//abstract class Shader(type: String) {
//
//    companion object {
//        private fun loadShader(shader: String, type: Int): Int {
//            var shader = shader
//            val shaderDir = File(Constants.debugResourcesDir, "resources/shader")
//            shader = FileUtils.readFileToString(File(shaderDir, shader))
//            val shaderId = glCreateShader(type)
//            glShaderSource(shaderId, shader)
//            glCompileShader(shaderId)
//
//            if (glGetShaderi(shaderId, GL_COMPILE_STATUS) == GL_FALSE) {
//                println("Failed to compile shader, aborting mission captain!")
//                println(glGetShaderInfoLog(shaderId, 500))
//            }
//
//            return shaderId
//        }
//    }
//
//    private var vertexShaderId: Int = -1
//    private var fragmentShaderId: Int = -1
//    private var programId: Int = -1
//    private val uniformLocationMap = mutableMapOf<String, Int>()
//
//    private var matrixBuffer: FloatBuffer = BufferUtils.createFloatBuffer(16)
//    private var lastUsedProgramId = -1
//    init {
//        vertexShaderId = loadShader("default.vert", GL_VERTEX_SHADER)
//        fragmentShaderId = loadShader("$type/$type.frag", GL_FRAGMENT_SHADER)
//        programId = glCreateProgram()
//        glAttachShader(programId, vertexShaderId)
//        glAttachShader(programId, fragmentShaderId)
//        glLinkProgram(programId)
//        glValidateProgram(programId)
//    }
//
//    fun setFloat(location: String, value: Float) {
//        glUniform1f(getUniformLocation(location), value)
//    }
//
//    fun setVector3f(location: String, value: Vector3f) {
//        glUniform3f(getUniformLocation(location), value.x, value.y, value.z)
//    }
//    fun setVector4f(location: String, value: Vector4f) {
//        glUniform4f(getUniformLocation(location), value.x, value.y, value.z, value.w)
//    }
//    fun setVector2f(location: String, value: Vector2f) {
//        glUniform2f(getUniformLocation(location), value.x, value.y)
//    }
//
//    fun setBoolean(location: String, value: Boolean) {
//        glUniform1i(getUniformLocation(location), if (value) 1 else 0)
//    }
//    fun setInt(location: String, value: Int) {
//        glUniform1i(getUniformLocation(location), value)
//    }
//
//    fun setMatrix4f(location: String, value: Matrix4f) {
//        value.store(matrixBuffer)
//        matrixBuffer.flip()
//        glUniformMatrix4(getUniformLocation(location), false, matrixBuffer)
//    }
//
//    open fun start() {
//        lastUsedProgramId = glGetInteger(GL_CURRENT_PROGRAM)
//        glUseProgram(programId)
//    }
//
//    open fun stop() {
//        glUseProgram(lastUsedProgramId)
//    }
//
//    fun clean() {
//        stop()
//        glDetachShader(programId, vertexShaderId)
//        glDetachShader(programId, fragmentShaderId)
//        glDeleteShader(vertexShaderId)
//        glDeleteShader(fragmentShaderId)
//        glDeleteProgram(programId)
//    }
//
//    fun bindAttribute(attribute: Int, name: String) {
//        glBindAttribLocation(programId, attribute, name)
//    }
//
//    private fun getUniformLocation(location: String): Int =
//        uniformLocationMap.getOrPut(location) { glGetUniformLocation(programId, location) }
//
//
//}