package net.melonclient.minecraft189;

import net.melonclient.client.Client;
import net.melonclient.minecraft189.impl.minecraft.MinecraftApi;
import net.melonclient.minecraftapi.MinecraftApiInterfacingAgent;

public class ClientInitializer {
    public static final ClientInitializer INSTANCE = new ClientInitializer();

    public void startup() {

        MinecraftApiInterfacingAgent.INSTANCE.construct(new MinecraftApi());

        Client.INSTANCE.startup();
    }
}
