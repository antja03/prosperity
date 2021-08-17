package net.melonclient.minecraft189;

import net.melonclient.client.Client;
import net.melonclient.minecraft189.impl.lwjgl.LwjglApi;
import net.melonclient.minecraft189.impl.minecraft.MinecraftApi;
import net.melonclient.minecraftapi.LwjglApiInit;
import net.melonclient.minecraftapi.MinecraftApiInit;

public class ClientInitializer {
    public static final ClientInitializer INSTANCE = new ClientInitializer();

    public void startup() {

        MinecraftApiInit.INSTANCE.construct(new MinecraftApi());
        LwjglApiInit.INSTANCE.construct(new LwjglApi());
        Client.INSTANCE.startup();
    }
}
