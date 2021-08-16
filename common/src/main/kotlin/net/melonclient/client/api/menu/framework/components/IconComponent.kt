package melon.api.menu.framework.components

import net.melonclient.client.api.menu.framework.Component
import net.melonclient.client.resource.ResourceManager
import net.melonclient.client.utils.Render

class IconComponent(posX: Int, posY: Int, width: Int, height: Int, val imgName: String, val imgSize: Int): Component(posX, posY, width, height) {
    
    override fun renderComponent() {
        val texId = ResourceManager.getTextureId(imgName, imgSize)
        Render.drawImg(texId, posX, posY, width, height)
    }
    
}