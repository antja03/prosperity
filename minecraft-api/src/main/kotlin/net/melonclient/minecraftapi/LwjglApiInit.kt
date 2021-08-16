package net.melonclient.minecraftapi

object LwjglApiInterfacingAgent {
    lateinit var lwjglApi: LwjglApi
    
    private var active = false
    
    
    fun construct(lwjglApi: LwjglApi) {
        println("Bound lwjgl api to ${lwjglApi.version}")
        this.lwjglApi = lwjglApi
        this.active = true
    }
    
}