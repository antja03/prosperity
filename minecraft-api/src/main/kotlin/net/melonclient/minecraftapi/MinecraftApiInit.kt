package net.melonclient.minecraftapi

object MinecraftApiInterfacingAgent {
    lateinit var minecraftApi: MinecraftApi
    
    private var active = false
    
    
    fun construct(minecraftApi: MinecraftApi) {
        println("Bound minecraft api to ${minecraftApi.gameVersion}")
        this.minecraftApi = minecraftApi
        this.active = true
    }
    
}