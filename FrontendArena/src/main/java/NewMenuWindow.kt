import Filters.IntFilter
import org.uqbar.arena.bindings.ObservableProperty
import org.uqbar.arena.bindings.PropertyAdapter
import org.uqbar.arena.layout.ColumnLayout
import org.uqbar.arena.layout.HorizontalLayout
import org.uqbar.arena.layout.VerticalLayout
import org.uqbar.arena.widgets.*
import org.uqbar.arena.widgets.List
import org.uqbar.arena.windows.SimpleWindow
import org.uqbar.arena.windows.WindowOwner
import org.uqbar.lacar.ui.model.ControlBuilder

class NewMenuWindow(owner : WindowOwner,model : MenuModel, val tipo : String) : SimpleWindow<MenuModel>(owner,model){
    override fun createFormPanel(p0: Panel) {
        modelObject.initMenuModif()
        checkProductLists(model.source.toAdd,model.source.added)
        Label(p0).setText(tipo).setWidth(100)
        createLabelAndText(p0,"Nombre","nameTemp")
        createLabelAndText(p0,"Descr","descriptionTemp")
        val panHor = Panel(p0).setLayout(HorizontalLayout())
        createLabelAndDrop(panHor,"TipoDesc.", "optionalDiscountTemp")
        createLabelAndTextWithFilter(panHor,"Desc","discountAmountTemp",25)
        val hPanel = Panel(p0).setLayout(HorizontalLayout())
        val l = List<ProductModel>(hPanel)
        l.onSelection { print("hola") }
        l.setHeight(200)
        l.setWidth(200)
        //l.bindItemsToProperty("toAdd").setModelToView()
        l.bindValueToProperty<ProductModel,ControlBuilder>("selectedProductL")
        val bindingItems = l.bindItems( ObservableProperty<ProductModel>(model,"toAdd"));
        bindingItems.setAdapter( PropertyAdapter(ProductModel::class.java, "name"))

        val vPanel = Panel(hPanel).setLayout(VerticalLayout())
        Button(vPanel).onClick( { model.source.changeList(model.source.selectedProductL,model.source.toAdd,model.source.added ) } )
                .setCaption(">>")

        Button(vPanel).onClick( { model.source.changeList(model.source.selectedProductR,model.source.added,model.source.toAdd ) } )
                .setCaption("<<")
        val l2 = List<ProductModel>(hPanel)
        l2.setHeight(200)
        l2.setWidth(200)

        //l.bindItemsToProperty("toAdd").setModelToView()
        l2.bindValueToProperty<ProductModel,ControlBuilder>("selectedProductR")
        val bindingItems2 = l2.bindItems( ObservableProperty<ProductModel>(model,"added"));
        bindingItems2.setAdapter( PropertyAdapter(ProductModel::class.java, "name"))

        createLabelAndCheck(p0,"Habil","enabledTemp")

        val grid = Panel(p0).setLayout(ColumnLayout(2))
        Label(grid)
        val hp = Panel(grid).setLayout(HorizontalLayout())
        val aceptar = Button(hp).setCaption("Aceptar").onClick  {

            modelObject.code = modelObject.codeTemp
            modelObject.name = modelObject.nameTemp
            modelObject.description = modelObject.descriptionTemp
            modelObject.products = modelObject.productsTemp
            modelObject.optionalDiscount = modelObject.optionalDiscountTemp
            modelObject.discountAmount = modelObject.discountAmountTemp
            modelObject.enabled = modelObject.enabledTemp

            model.source.syncMenu()


            if (!ApplicationModel.menus.contains(model.source)) {
                ApplicationModel.selectedRestaurant?.menus?.add(model.source.menu)
                ApplicationModel.menus.add(model.source)
            }


            close()

        }
        val cancelar = Button(hp)
        cancelar.onClick { close() }
        cancelar.setCaption("Cancelar")

    }

    fun createLabelAndText(p : Panel?, name : String, toBind : String, textbSize : Int  = 300){
        val p2 = Panel(p)
        p2.setLayout(HorizontalLayout())
        Label(p2).setText(name).setWidth(60)
        val t = TextBox(p2)
        t.setWidth(textbSize)
        t.bindValueToProperty<String, ControlBuilder>(toBind)
    }

    fun createLabelAndTextWithFilter(p : Panel?, name : String, toBind : String, textbSize : Int  = 300){
        val p2 = Panel(p)
        p2.setLayout(HorizontalLayout())
        Label(p2).setText(name).setWidth(60)
        val t = TextBox(p2)
        t.setWidth(textbSize)
        t.bindValueToProperty<String, ControlBuilder>(toBind)
        t.withFilter(IntFilter())
    }


    fun createLabelAndDrop(p : Panel?, name : String, toBind : String){
        Label(p).setText(name)
        val s = Selector<ProductModel>(p).allowNull(false);
        s.bindValueToProperty<ProductModel,ControlBuilder>("optionalDiscount")
        s.bindItemsToProperty("op")
        s.setWidth(200)




    }


    fun createLabelAndCheck(p : Panel?, name : String, toBind : String){
        val p2 = Panel(p)
        p2.setLayout(HorizontalLayout())
        Label(p2).setText(name)
        CheckBox(p2).bindValueToProperty<MenuModel,ControlBuilder>(toBind)






    }
    override fun addActions(p0: Panel?) {
    }

    fun checkProductLists(Lizq : MutableList<ProductModel> , Lder : MutableList<ProductModel>){
            Lizq.removeAll { x -> Lder.any{ y -> y.name == x.name} }
        }

}