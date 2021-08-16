package net.melonclient.client.api.event.type

import net.melonclient.client.api.event.Event

open class CancellableEvent: Event() {
    
    private var cancelled = false
    
    fun isCancelled(): Boolean {
        return cancelled
    }
    
    fun cancel() {
        cancelled = true
    }
    
    override fun <T> dispatch() :T {
        cancelled = false
        return super.dispatch()
    }
}