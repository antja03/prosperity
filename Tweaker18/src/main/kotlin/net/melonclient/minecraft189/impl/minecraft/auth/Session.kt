package net.melonclient.minecraft189.impl.minecraft.auth

import net.melonclient.minecraftapi.api.minecraft.auth.Session
import net.minecraft.client.Minecraft
import java.util.*

object Session : Session {
    
    override val sessionId: String
        get() = Minecraft.getMinecraft().session.sessionID
    override val uuid: String
        get() = Minecraft.getMinecraft().session.playerID
    override val username: String
        get() = Minecraft.getMinecraft().session.username
    
}