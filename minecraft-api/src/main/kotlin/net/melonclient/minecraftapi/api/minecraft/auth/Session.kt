package net.melonclient.minecraftapi.api.minecraft.auth

interface Session {
    val sessionId: String
    
    val uuid: String
    
    val username: String
}