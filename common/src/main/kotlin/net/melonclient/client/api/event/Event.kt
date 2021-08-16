package net.melonclient.client.api.event

open class Event {
    
    @Suppress("UNCHECKED_CAST")
    open fun <T> dispatch(): T {
        EventBus.pubSub.post(this).dispatch()
        return this as T
    }
    
}