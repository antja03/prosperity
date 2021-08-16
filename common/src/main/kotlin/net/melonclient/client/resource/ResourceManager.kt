package net.melonclient.client.resource

import net.melonclient.client.Constants
import java.awt.Font
import java.awt.GraphicsEnvironment
import java.io.*
import java.nio.file.Files
import java.util.jar.JarFile

object ResourceManager {
    
    private const val RESOURCE_DIR = "resources"
    private const val TEXTURE_DIR = "texture"
    private const val FONT_DIR = "font"
    private const val SEPARATOR = "\\"
    
    private val resourceLocation =
        if (Constants.DEBUG) File(Constants.debugResourcesDir, RESOURCE_DIR)
        else Constants.rootDir
    
    private val textureMap = hashMapOf<String, Texture>()
    
    fun startup() {
        if (!Constants.DEBUG)
            extractResources()
        registerTextures()
        registerFonts()
    }
    
    fun getTextureId(name: String, size: Int): Int {
        textureMap["$name:$size"]?.let { texture ->
            return texture.id
        } ?: return -1
    }
    
    private fun extractResources() {
        val jarFile = JarFile(javaClass.protectionDomain.codeSource.location.path.replace("%20", " "))
        val entries = jarFile.entries()
        
        while (entries.hasMoreElements()) {
            val entry = entries.nextElement()
            
            if (!entry.name.startsWith("resources"))
                continue
            
            val fileToWrite = File(resourceLocation, entry.name)
            
            if (entry.isDirectory) {
                fileToWrite.mkdirs()
                continue
            }
            
            val input = BufferedInputStream(jarFile.getInputStream(entry))
            val output = BufferedOutputStream(FileOutputStream(fileToWrite))
            val buffer = ByteArray(2048)
            
            while (true) {
                val nBytes = input.read(buffer)
                
                if (nBytes <= 0)
                    break
                
                output.write(buffer, 0, nBytes)
            }
            
            output.flush()
            output.close()
            input.close()
        }
    }
    
    private fun registerTextures() {
        val textureLocation = File(resourceLocation, TEXTURE_DIR)
        val paths = Files.walk(textureLocation.toPath())
        
        paths.forEach paths@ { path ->
            val asFile = path.toFile()
            
            if (asFile.isDirectory)
                return@paths
            
            if (asFile.extension != "png")
                return@paths
            
            val name = asFile.parentFile.nameWithoutExtension
            val size = asFile.nameWithoutExtension
            textureMap["$name:$size"] = Texture(asFile)
        }
    }
    
    private fun registerFonts() {
        val graphicsEnv = GraphicsEnvironment.getLocalGraphicsEnvironment()
        val fontLocation = File(resourceLocation, FONT_DIR)
        val paths = Files.walk(fontLocation.toPath())
    
        paths.forEach paths@ { path ->
            val asFile = path.toFile()
        
            if (asFile.isDirectory)
                return@paths
        
            if (asFile.extension != "ttf")
                return@paths
    
            val font = Font.createFont(Font.TRUETYPE_FONT, asFile.inputStream())
            graphicsEnv.registerFont(font)
        }
    }
}
