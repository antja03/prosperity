package net.melonclient.client.api.menu.dsl

import melon.api.menu.framework.components.*
import net.melonclient.client.api.menu.framework.components.ButtonComponent
import net.melonclient.client.api.menu.framework.components.ContainerComponent
import net.melonclient.client.api.menu.framework.components.StatedIconTextButtonComponent
import net.melonclient.client.data.reference.MutableReference

fun ContainerComponent.button(posX: Int, posY: Int, width: Int, height: Int, onClick: () -> Unit = {}): ButtonComponent {
    val button = ButtonComponent(this.posX + posX, this.posY + posY, width, height, onClick)
    this.addComponent(button)
    return button
}

fun ContainerComponent.icon(posX: Int, posY: Int, width: Int, height: Int, imgName: String, imgSize: Int): IconComponent {
    val icon = IconComponent(this.posX + posX, this.posY + posY, width, height, imgName, imgSize)
    this.addComponent(icon)
    return icon
}

fun ContainerComponent.iconButton(posX: Int, posY: Int, imgName: String, imgSize: Int, onClick: () -> Unit = {}): IconButtonComponent {
    val iconButton = IconButtonComponent(this.posX + posX, this.posY + posY, imgName, imgSize, onClick)
    this.addComponent(iconButton)
    return iconButton
}

fun ContainerComponent.iconButtonWithText(posX: Int, posY: Int, text: String, imgName: String, imgSize: Int, imgAngle: Float = 0f, onClick: () -> Unit = {}): IconTextButtonComponent {
    val iconButton = IconTextButtonComponent(this.posX + posX, this.posY + posY, text, imgName, imgSize, imgAngle, onClick)
    this.addComponent(iconButton)
    return iconButton
}

// START STATED ICON BUTTON WITH TEXT
fun ContainerComponent.statedIconButtonWithText(reference: MutableReference<Boolean>, posX: Int, posY: Int, width: Int, height: Int, text: String, imgName: String, imgSize: Int, imgAngle: Float = 0f, onClick: () -> Unit = {}): StatedIconTextButtonComponent {
    val iconButton = StatedIconTextButtonComponent(reference, this.posX + posX, this.posY + posY, width, height, text, imgName, imgSize, imgAngle, onClick)
    this.addComponent(iconButton)
    return iconButton
}

fun ContainerComponent.statedIconButtonWithText(reference: MutableReference<Boolean>, posX: Int, posY: Int,  text: String, imgName: String, imgSize: Int, imgAngle: Float = 0f, onClick: () -> Unit = {}): StatedIconTextButtonComponent {
    val iconButton = StatedIconTextButtonComponent(reference, this.posX + posX, this.posY + posY, 240, 40, text, imgName, imgSize, imgAngle, onClick)
    this.addComponent(iconButton)
    return iconButton
}
// END STATED ICON BUTTON WITH TEXT
