package net.melonclient.client.api.event

import me.hippo.api.lwjeb.bus.PubSub
import me.hippo.api.lwjeb.configuration.BusConfigurations
import me.hippo.api.lwjeb.configuration.config.impl.BusPubSubConfiguration
import me.hippo.api.lwjeb.subscribe.impl.StrongReferencedListenerSubscriber

object EventBus {
    
    val pubSub = PubSub<Event>(BusConfigurations.Builder().setConfiguration(BusPubSubConfiguration::class.java) {
        val configuration = BusPubSubConfiguration.getDefault()
        configuration.setSubscriber(StrongReferencedListenerSubscriber<Event>())
        configuration
    }.build())
    
}