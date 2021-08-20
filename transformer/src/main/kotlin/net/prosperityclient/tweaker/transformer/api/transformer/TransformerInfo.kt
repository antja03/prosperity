package net.prosperityclient.tweaker.transformer.api.transformer

import kotlin.reflect.KClass

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
annotation class TransformerInfo(val name: KClass<out Any>, val version: Double)
