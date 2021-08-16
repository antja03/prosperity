package net.melonclient.client.api.menu.dsl

import net.melonclient.client.api.menu.framework.Page
import net.melonclient.client.api.menu.framework.components.ContainerComponent

fun Page.container(posX: Int, posY: Int, width: Int, height: Int, init: ContainerComponent.() -> Unit): ContainerComponent {
    val container = ContainerComponent(this.posX + posX, this.posY + posY, width, height)
    container.init()
    addComponent(container)
    return container
}