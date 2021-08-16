package net.melonclient.client

import java.io.File
import java.util.*

object Constants {
    
    const val DEBUG = true
    
    val debugRootDir = File(javaClass.protectionDomain.codeSource.location.path.replace("%20", " ")).parentFile
    val debugResourcesDir = File(debugRootDir, "resources")
    val rootDir = File(System.getProperty("user.home"), "melonclient")
    
    val random = Random()
    
    //    val gson = GsonBuilder().setPrettyPrinting().create()
    val filePath = File(System.getProperty("user.home"), "melon").absolutePath
    
}