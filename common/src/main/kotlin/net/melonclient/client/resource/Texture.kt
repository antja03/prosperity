package net.melonclient.client.resource

import net.melonclient.client.Client
import java.io.File
import java.io.FileInputStream

data class Texture(var id: Int) {
    
    constructor(file: File) : this(Client.lwjglApi.intermediateOpenGL.genTextures()){
        val inputStream = FileInputStream(file)
//        val bufferedImage = TextureUtil.readBufferedImage(inputStream)
//        TextureUtil.uploadTextureImageAllocate(id, bufferedImage, false, true)
    }
    
}
