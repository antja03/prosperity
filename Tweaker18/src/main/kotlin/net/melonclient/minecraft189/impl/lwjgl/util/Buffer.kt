package net.melonclient.minecraft189.impl.lwjgl.util

import net.melonclient.minecraftapi.api.lwjgl.util.Buffer
import org.lwjgl.BufferUtils
import java.nio.ByteBuffer

class Buffer : Buffer {
    override fun createByteBuffer(allocate: Int): ByteBuffer = BufferUtils.createByteBuffer(allocate)
}