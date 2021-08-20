package net.prosperityclient.tweaker.transformer

import com.google.gson.JsonObject
import com.google.gson.JsonParser
import net.prosperityclient.tweaker.transformer.api.loader.Loader
import net.prosperityclient.tweaker.transformer.api.transformer.Transformer
import kotlin.math.abs

object TransformerParser {
    var initialized = false
    
    private const val version = 1.0
    
    private val config = mutableMapOf<String, List<String>>()
    
    
    fun init(loader: Loader) {
        if (initialized) {
            error("[Transformer]: Attempting to initialize an initialized object")
        }
        config.forEach { (name, transformerList) ->
            transformerList.forEach { transformer ->
                val clazz = Class.forName(transformer).getDeclaredConstructor().newInstance() as Transformer
                loader.add(clazz)
                println("[Transformer]: Initialized $transformer for the project $name")
            }
        }
        initialized = true
        println("[Transformer]: Finished registering transformers, it's all up to you, LaunchWrapper")
    }
    
    fun addConfig(name: String) {
        val file = this::class.java.classLoader.getResource(name).openStream()
        
        if (file != null) {
            val transformerName = name.removePrefix("transformers.").removeSuffix(".json")
            val transformers = parseTransformers(file.bufferedReader().readText())
            config[transformerName] = transformers
            println("[Transformer]: Successfully registered \"${transformerName}\"")
        } else {
            error("[Transformer]: \"${name}\" does not exist.")
        }
    }
    
    private fun parseTransformers(from: String): List<String> {
        val `object` = JsonParser().parse(from) as JsonObject
        if (`object`.has("transformerVersion")) {
            val asDouble = `object`.get("transformerVersion").asDouble
            if (abs(asDouble - version) >= 1) {
                error("[Transformer]: Version mismatch (too out of date/new) - (expected $version, got $asDouble)")
            }
        }
        if (`object`.has("package")) {
            val `package` = `object`.get("package").asString
            return if (`object`.has("transformers")) {
                val array = `object`.get("transformers").asJsonArray
                array.map { "$`package`.${it.asString}" }
            } else {
                emptyList()
            }
        }
        return emptyList()
    }
}