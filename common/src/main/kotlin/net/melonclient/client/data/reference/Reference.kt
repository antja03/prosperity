package net.melonclient.client.data.reference

// Wrapper for primitive or non primitive types
interface Reference<T> {
    val defaultValue: T
    var value: T
}