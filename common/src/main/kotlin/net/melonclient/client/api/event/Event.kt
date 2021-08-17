package net.melonclient.client.api.event

open class Event {
    
    @Suppress("UNCHECKED_CAST")
    open fun <T> dispatch() {
        EventBus.pubSub.post(this).dispatch()
    }
    
}