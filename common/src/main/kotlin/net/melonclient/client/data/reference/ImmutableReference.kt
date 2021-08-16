package net.melonclient.client.data.reference

class ImmutableReference<T>(override var value: T, override val defaultValue: T = value): Reference<T>