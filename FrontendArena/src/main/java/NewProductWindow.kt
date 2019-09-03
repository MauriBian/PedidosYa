import Filters.IntFilter
import Filters.JustOneDotFilter
import Transformers.EnumTransformer
import org.uqbar.arena.bindings.ObservableProperty
import org.uqbar.arena.filters.TextFilter
import org.uqbar.arena.layout.ColumnLayout
import org.uqbar.arena.layout.HorizontalLayout
import org.uqbar.arena.widgets.*
import org.uqbar.arena.windows.SimpleWindow
import org.uqbar.arena.windows.WindowOwner
import org.uqbar.lacar.ui.model.ControlBuilder

class NewProductWindow(owner : WindowOwner,model : ProductModel,val tipoVentana : String) : SimpleWindow<ProductModel>(owner,model){
    override fun createFormPanel(p0: Panel) {
        modelObject.initModifProduct()
        Label(p0).setText(tipoVentana).setFontSize(30)
        val panelColumn : Panel = Panel(p0)
        .setLayout(ColumnLayout(2))
        createLabelAndText(panelColumn,"Nombre","nameTemp",175)
        createLabelAndText(panelColumn,"Descr","descriptionTemp",175)
        val panelGrid: Panel = Panel(p0)
        panelGrid.setLayout(HorizontalLayout())
        createLabelAndTextWithFilter(panelGrid,"Precio    ","priceTemp",133,JustOneDotFilter())
        val selector = Selector<Category>(panelGrid)
        selector.bindValueToProperty<Category, ControlBuilder>("categoryTemp")
        selector.bindItems<String>(ObservableProperty(model, "categories"))
        selector.setWidth(175)
        val panelButton = Panel(p0)
        panelButton.setLayout(HorizontalLayout())
        val accept = Button(panelButton)
                .setCaption("Aceptar")
        accept.setWidth(189)
        accept.onClick {
            modelObject.code = modelObject.codeTemp
            modelObject.name = modelObject.nameTemp
            modelObject.description = modelObject.descriptionTemp
            modelObject.price = modelObject.priceTemp
            modelObject.category = modelObject.categoryTemp
            model.source.syncProduct()
            if (!ApplicationModel.products.contains(model.source)) {
                ApplicationModel.products.add(model.source)

            }





            close()

        }

        val cancel = Button(panelButton)
                .setCaption("Cancelar")
        cancel.setWidth(189)
        cancel.onClick{
            close()
        }

    }

    fun createLabelAndText(p : Panel, name : String, toBind : String,width : Int) {
        Label(p).setText(name)
        TextBox(p)
                .setWidth(width)
                .bindValueToProperty<String, ControlBuilder>(toBind)

    }

    fun createLabelAndTextWithFilter(p : Panel, name : String, toBind : String, width : Int, filter : TextFilter) {
        Label(p).setText(name)
        TextBox(p).withFilter(IntFilter())
                .setWidth(width)
                .bindValueToProperty<String, ControlBuilder>(toBind)

    }




    override fun addActions(p0: Panel?) {
    }



}