package net.melonclient.client.api.event.filter

import me.hippo.api.lwjeb.filter.MessageFilter
import net.melonclient.client.api.event.type.PrePostEvent

class PreFilter: MessageFilter<PrePostEvent> {
    
    override fun passes(event: PrePostEvent): Boolean {
        return event.isPre()
    }
    
}