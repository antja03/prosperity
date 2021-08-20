package net.melonclient.client.api.event

import rip.hippo.lwjeb.bus.PubSub
import rip.hippo.lwjeb.configuration.BusConfigurations
import rip.hippo.lwjeb.configuration.config.impl.BusPubSubConfiguration
import rip.hippo.lwjeb.subscribe.impl.StrongReferencedListenerSubscriber

object EventBus {
    
    val pubSub = PubSub<Event>(BusConfigurations.Builder().setConfiguration(BusPubSubConfiguration::class.java) {
        val configuration = BusPubSubConfiguration.getDefault()
        configuration.setSubscriber(StrongReferencedListenerSubscriber<Event>())
        configuration
    }.build())
    
}