package net.melonclient.client.api.menu.framework

class NestablePage(posX: Int, posY: Int, width: Int, height: Int): Page(posX, posY, width, height) {

    private val pages = arrayListOf<Page>()
    private var activePage: Page? = null
    
    override fun renderComponent() {
        super.renderComponent()
        activePage?.renderComponent()
    }
    
    override fun onMouseDown(mouseButton: Int): Boolean {
        if (super.onMouseDown(mouseButton))
            return true
        
        if (activePage?.onMouseDown(mouseButton) == true)
            return true
        
        return false
    }
    
    override fun move(difX: Int, difY: Int) {
        super.move(difX, difY)
        for (page in pages)
            page.move(difX, difY)
        
    }
    
    fun default() {
        activePage = pages.get(0)
    }
    
    fun addPage(page: Page): Page {
        pages.add(page)
        return page
    }
    
    fun setPage(page: Page?) {
        activePage = page
    }

}