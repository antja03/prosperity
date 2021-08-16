package net.melonclient.client.api.event.type

import net.melonclient.client.api.event.Event

open class PrePostEvent: Event() {
    
    var post = false
    
    fun isPre(): Boolean {
        return !post
    }
    
    fun isPost(): Boolean {
        return post
    }
    
    fun makePre(): PrePostEvent {
        post = false
        return this
    }
    
    fun makePost(): PrePostEvent {
        post = true
        return this
    }
    
}