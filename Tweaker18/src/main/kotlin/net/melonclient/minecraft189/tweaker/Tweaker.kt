package net.melonclient.minecraft189.tweaker

import net.minecraft.launchwrapper.IClassTransformer
import net.minecraft.launchwrapper.ITweaker
import net.minecraft.launchwrapper.LaunchClassLoader
import net.prosperityclient.tweaker.transformer.TransformerParser
import net.prosperityclient.tweaker.transformer.api.loader.Loader
import net.prosperityclient.tweaker.transformer.api.transformer.Transformer
import net.prosperityclient.tweaker.utils.ClassManipulationUtil
import java.io.File

class Tweaker : ITweaker, Loader {
    private val args = mutableListOf<String>()
    private val transformers = mutableListOf<Transformer>()
    
    override fun acceptOptions(args: MutableList<String>?, gameDir: File?, assetsDir: File?, profile: String?) {
        this.args.addAll(args!!)
        addArg("gameDir", gameDir)
        addArg("assetsDir", assetsDir)
        addArg("version", profile)
        TransformerParser.addConfig("transformers.prosperity189.json")
    }
    
    override fun injectIntoClassLoader(classLoader: LaunchClassLoader?) {
        TransformerParser.init(this)
        transformers.forEach {
            if (classLoader != null) {
                fuckLaunchWrapper(classLoader).add(IClassTransformer { name, _, basicClass ->
                    when (basicClass) {
                        null -> {
                            ByteArray(0)
                        }
                        else -> {
                            if (name == it.name) {
                                val node = ClassManipulationUtil.getNodeFromBytes(basicClass)
                                it.transform(node)
                               return@IClassTransformer ClassManipulationUtil.getBytesFromNode(node)
                            }

                            basicClass
                        }
                    }
                })
            }
        }
        println()
    }
    
    override fun getLaunchTarget(): String? {
        return "net.minecraft.client.main.Main"
    }
    
    override fun getLaunchArguments(): Array<String?>? {
        return args.toTypedArray()
    }
    
    private fun addArg(label: String, file: File?) {
        if (file != null) addArg(label, file.absolutePath)
    }
    
    private fun addArg(label: String, value: String?) {
        if (!args.contains("--$label") && value != null) {
            args.add("--$label")
            args.add(value)
        }
    }
    
    override fun add(transformer: Transformer) {
        transformers += transformer
    }
    
    private fun fuckLaunchWrapper(launchClassLoader: LaunchClassLoader): MutableList<IClassTransformer> {
        launchClassLoader::class.java.declaredFields.forEach {
            if (it.name == "transformers") {
                it.isAccessible = true
                return it.get(launchClassLoader)!! as MutableList<IClassTransformer>
            }
        }
        return mutableListOf<IClassTransformer>()
    }
    
}