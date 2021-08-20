package net.melonclient.client.api.event.filter

import rip.hippo.lwjeb.filter.MessageFilter
import net.melonclient.client.api.event.type.PrePostEvent

class PostFilter: MessageFilter<PrePostEvent> {
    
    override fun passes(event: PrePostEvent): Boolean {
        return event.isPost()
    }
    
}