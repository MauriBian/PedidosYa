import Transformers.EnumTransformer
import Transformers.TrueFalseTransformer
import org.apache.commons.collections15.Transformer
import org.uqbar.arena.layout.ColumnLayout
import org.uqbar.arena.layout.HorizontalLayout
import org.uqbar.arena.widgets.*
import org.uqbar.arena.widgets.tables.Column
import org.uqbar.arena.widgets.tables.Table
import org.uqbar.arena.windows.SimpleWindow
import org.uqbar.arena.windows.WindowOwner
import org.uqbar.commons.model.IModel
import org.uqbar.commons.model.exceptions.UserException
import org.uqbar.lacar.ui.model.ControlBuilder

class MainWindow(owner : WindowOwner, model : ApplicationModel) : SimpleWindow<ApplicationModel>(owner,model) {

    override fun addActions(actionsPanel: Panel) { /* Actions Panel */
    }

    override fun createFormPanel(mainPanel: Panel) {

        Label(mainPanel).setText("") //label invisible por el panel de error q quita una columna
        mainPanel.setLayout(ColumnLayout(2))
        Label(mainPanel).setText("Administrar Productos").setFontSize(15)
        Label(mainPanel).setText(("Administrar Menus")).setFontSize(15)

        val searcherL = Panel(mainPanel)
        searcherL.setLayout(HorizontalLayout())
        val searchBL = Button(searcherL).setCaption("Buscar")
        searchBL.onClick {
            model.source.makeSearchProducts()
            this.close()
            MainWindow(owner,model.source).open()
        }
        TextBox(searcherL).setWidth(400).bindValueToProperty<ApplicationModel,ControlBuilder>("searcherL")
        val searcherR = Panel(mainPanel)
        searcherR.setLayout(HorizontalLayout())
        val searchBR = Button(searcherR).setCaption("Buscar")
        searchBR.onClick {
            model.source.makeSearchMenus()
            this.close()
            MainWindow(owner,model.source).open()}
        TextBox(searcherR).setWidth(400).bindValueToProperty<ApplicationModel,ControlBuilder>("searcherR")


        val tabla1 = Panel(mainPanel)
        val tablaProd = Table<ProductModel>(tabla1,ProductModel::class.java)
        tablaProd.setNumberVisibleRows(8)
        tablaProd.bindItemsToProperty("products")
        tablaProd.bindValueToProperty<ProductModel, ControlBuilder>("selectedProduct")
        Column<ProductModel>(tablaProd)
                 .setTitle("Producto")
                 .setFixedSize(250)
                 .bindContentsToProperty("name")
        Column<ProductModel>(tablaProd)
                .setTitle("precio")
                .setFixedSize(250)
                .bindContentsToProperty("price")

        val tabla2 = Panel(mainPanel)
        val tablaMenu = Table<MenuModel>(tabla2,MenuModel::class.java)
        tablaMenu.bindItemsToProperty("menus")
        tablaMenu.setNumberVisibleRows(8)
        tablaMenu.bindValueToProperty<MenuModel, ControlBuilder>("selectedMenu")
        Column<MenuModel>(tablaMenu)
                .setTitle("Menu")
                .setFixedSize(225)
                .bindContentsToProperty("name")

        Column<MenuModel>(tablaMenu)
                .setTitle("Precio")
                .setFixedSize(225)
                .bindContentsToProperty("totalPrice")
        Column<MenuModel>(tablaMenu)
                .setTitle("Habil?")
                .setFixedSize(50)
                .bindContentsToProperty("enabled")
                .setTransformer(TrueFalseTransformer())

        val panelBotonesizq = Panel(mainPanel)
        panelBotonesizq.setLayout(ColumnLayout(2))
        var largo : Int = 247
        createButton(panelBotonesizq,largo,"Ver").onClick{
                                                        val infoProductDialog = InfoProductDialog(this, modelObject)
                                                        infoProductDialog.setMessage("info del producto " + model.source.selectedProduct?.name, model.source.selectedProduct!!)
                                                    infoProductDialog.open()}
        createButton(panelBotonesizq,largo,"Ver Menus").onClick { viewProduct(model.source.selectedProduct, model.source.menus) }
        createButton(panelBotonesizq,largo,"Agregar").onClick { NewProductWindow(owner,ProductModel(),"Nuevo Producto").open()
        this.close()
        MainWindow(owner,ApplicationModel).open()}
        createButton(panelBotonesizq,largo,"Modificar").onClick { NewProductWindow(owner,model.source.selectedProduct!!,"Modificar Producto").open() }
        createButton(panelBotonesizq,largo,"Eliminar").onClick{
                                                            val confirmDeleteProductDialog = ConfirmDialog(this, modelObject)
                                                            confirmDeleteProductDialog.setMessage("seguro que quiere elimirar el producto " + model.source.selectedProduct!!.name + "?")
                                                            confirmDeleteProductDialog.onAccept {
                                                            model.source.deleteProductSelected()
                                                            this.close()
                                                            MainWindow(owner,model.source).open()

                                                        }
                                                        confirmDeleteProductDialog.open()}


        val panelBotonesDer = Panel(mainPanel)
        panelBotonesDer.setLayout(HorizontalLayout())
        largo = 123
        createButton(panelBotonesDer,largo,"Ver").onClick { viewMenu(model.source.selectedMenu) }
        createButton(panelBotonesDer,largo,"Agregar").onClick { NewMenuWindow(owner,MenuModel(),"Nuevo Menu").open()
            this.close()
            MainWindow(owner,ApplicationModel).open()}
        createButton(panelBotonesDer,largo,"Modificar").onClick { NewMenuWindow(owner,model.source.selectedMenu!!,"Modificar Menu").open() }
        createButton(panelBotonesDer,largo,"Eliminar").onClick{
                                                           val confirmDeleteMenuDialog = ConfirmDialog(this, modelObject)
                                                           confirmDeleteMenuDialog.setMessage("seguro que quiere elimirar el menu " + model.source.selectedMenu?.name + "?")
                                                           confirmDeleteMenuDialog.onAccept {
                                                           model.source.deleteMenuSelected()
                                                           this.close()
                                                           MainWindow(owner,model.source).open()
                                                        }
                                                        confirmDeleteMenuDialog.open()}
    }

    fun viewMenu(menu:MenuModel?){
        if(menu != null) {
            val menuView = InformationWindow(owner, modelObject)
            val title :String = "Productos del " + menu.name
            val listProducts : MutableList<String> = menu.added.map{product-> product.name + " " + product.price.toString() }.toMutableList()
            println(menu.added)
            val result : String = "TOTAL: $" + menu.totalPrice().toString()
            menuView.setValues(title, listProducts, result)
            menuView.open()
        }
        else{
            throw UserException("elija un menu")
        }
    }

    fun viewProduct(product:ProductModel? , menus :MutableList<MenuModel> ){
        if(product != null) {
            val productView = InformationWindow(owner, modelObject)
            val title :String = "Menus que tienen " + product.name
            val listMenus : MutableList<String> = menusWhitThisProduct(product , menus)//toMutableList()
            productView.setValues(title, listMenus, "")
            productView.open()
        }
        else{
            throw UserException("elija un producto")
        }
    }

    fun menusWhitThisProduct(product: ProductModel, menus : MutableList<MenuModel> ): MutableList<String> {
        var menusWhitProudct : MutableList<MenuModel>  = menus.filter{ menu -> menu.products.any { proAndCount -> proAndCount.name == product.name}}.toMutableList()
        return menusWhitProudct.map { menu -> menu.name}.toMutableList()
    }

    fun createButton(panel : Panel, largo : Int , caption : String ) : Button{
        val boton = Button(panel).setCaption(caption)
        boton.setWidth(largo)

        return boton
    }

}