import org.uqbar.arena.aop.windows.TransactionalDialog
import org.uqbar.arena.layout.ColumnLayout
import org.uqbar.arena.widgets.*
import org.uqbar.arena.windows.Dialog
import org.uqbar.arena.windows.WindowOwner


class InfoProductDialog(owner: WindowOwner, model: ApplicationModel) : TransactionalDialog<ApplicationModel>(owner, model) {
    private var message = ""
    private var code = "codigo: "
    private var productName = "name: "
    private var description = "descripcion: "
    private var price = "precio: "
    private var category = "categoria: "


    override fun createFormPanel(mainPanel: Panel) {
        Label(mainPanel).setText(message).setFontSize(15)
        Label(mainPanel).setText(productName).alignLeft()
        Label(mainPanel).setText(description).alignLeft()
        Label(mainPanel).setText(price).alignLeft()
        Label(mainPanel).setText(category).alignLeft()

        val buttonsPanel = Panel(mainPanel)
        buttonsPanel.setLayout(ColumnLayout(2))

        Button(buttonsPanel)
                .setCaption("Aceptar")
                .onClick { accept() }
                .setWidth(100)
    }

    fun setMessage(mes: String, productModel: ProductModel) {
        message = mes
        productName = productName + productModel.name
        description = description + productModel.description
        price = price + productModel.price
        category = category + productModel.category
    }
}