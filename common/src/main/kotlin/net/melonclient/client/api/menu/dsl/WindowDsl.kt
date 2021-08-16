package net.melonclient.client.api.menu.dsl

import net.melonclient.client.api.menu.framework.NestablePage
import net.melonclient.client.api.menu.framework.Page
import net.melonclient.client.api.menu.framework.Window
import net.melonclient.client.api.menu.framework.components.ContainerComponent

fun window(width: Int, height: Int, init: Window.() -> Unit): Window {
    val window = Window(width, height)
    window.init()
    return window
}


fun Window.widthPercentage(percent: Double): Int {
    return (this.width * percent).toInt()
}

fun Window.page(posX: Int, posY: Int, width: Int, height: Int, init: Page.() -> Unit): Page {
    val page = Page(this.posX + posX, this.posY + posY, width, height)
    page.init()
    return topPage.addPage(page)
}

fun Window.nestablePage(posX: Int, posY: Int, width: Int, height: Int, init: Page.() -> Unit): NestablePage {
    val page = NestablePage(this.posX + posX, this.posY + posY, width, height)
    page.init()
    return page
}

fun Window.container(posX: Int, posY: Int, width: Int, height: Int, init: ContainerComponent.() -> Unit): ContainerComponent {
    val container = ContainerComponent(this.posX + posX, this.posY + posY, width, height)
    container.init()
    this.topPage.addComponent(container)
    return container
}