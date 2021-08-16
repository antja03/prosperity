package net.melonclient.minecraftapi.api.lwjgl.util

import java.nio.ByteBuffer

interface Buffer {
    fun createByteBuffer(allocate: Int): ByteBuffer
}