package net.melonclient.client.api.event.filter

import rip.hippo.lwjeb.filter.MessageFilter
import net.melonclient.client.api.event.type.PrePostEvent

class PreFilter: MessageFilter<PrePostEvent> {
    
    override fun passes(event: PrePostEvent): Boolean {
        return event.isPre()
    }
    
}