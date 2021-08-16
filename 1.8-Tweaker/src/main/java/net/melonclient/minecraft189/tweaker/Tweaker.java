package net.melonclient.minecraft189.tweaker;

import net.melonclient.minecraft189.transformer.transformers.EntityClassTransformer;
import net.melonclient.minecraft189.transformer.transformers.EntityLivingBaseTransformer;
import net.melonclient.minecraft189.transformer.transformers.MinecraftClassTransformer;
import net.melonclient.minecraft189.transformer.transformers.SessionTransformer;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.launchwrapper.ITweaker;
import net.minecraft.launchwrapper.LaunchClassLoader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Tweaker implements ITweaker {

    private List<String> args;

    @Override
    public void acceptOptions(List<String> args, File gameDir, File assetsDir, String profile) {
        (this.args = new ArrayList<>()).addAll(args);
        addArg("gameDir", gameDir);
        addArg("assetsDir", assetsDir);
        addArg("version", profile);
    }

    @Override
    public void injectIntoClassLoader(LaunchClassLoader classLoader) {
        classLoader.addClassLoaderExclusion("net.melonclient.minecraft189.transformer.transformers.");
        classLoader.registerTransformer(SessionTransformer.class.getName());
        classLoader.registerTransformer(EntityClassTransformer.class.getName());
        classLoader.registerTransformer(EntityLivingBaseTransformer.class.getName());
        classLoader.registerTransformer(MinecraftClassTransformer.class.getName());
    }

    @Override
    public String getLaunchTarget() {
        return "net.minecraft.client.main.Main";
    }

    @Override
    public String[] getLaunchArguments() {
        return args.toArray(new String[0]);
    }
    private void addArg(String label, File file) {
        if (file != null)
            addArg(label, file.getAbsolutePath());
    }

    private void addArg(String label, String value) {
        if (!args.contains("--" + label) && value != null) {
            this.args.add("--" + label);
            this.args.add(value);
        }
    }
}
