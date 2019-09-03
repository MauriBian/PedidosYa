//import jdk.jfr.Enabled
import ApplicationModel.selectedRestaurant
import org.uqbar.commons.model.annotations.Observable
import scala.App

@Observable
class MenuModel (var code : Int, override var name : String, var description : String, var products : MutableList<Product>, var optionalDiscount: OptionalDiscount, var discountAmount : Double, var enabled : Boolean) : Searchable {

    var menu : Menu
    val op = mutableListOf<OptionalDiscount>(OptionalDiscount.FIJO,OptionalDiscount.PORCENTUAL)
    var selectedProduct : ProductModel? = null
    var toAdd  = selectedRestaurant!!.getProducts().map { elem -> ProductModel().prodToProdModel(elem) }.toMutableList()
    var added : MutableList<ProductModel>  =products.map {elem -> ProductModel().prodToProdModel(elem)}.toMutableList()


    var codeTemp : Int = 0
    var nameTemp : String = ""
    var descriptionTemp : String = ""
    var productsTemp : MutableList<Product> = mutableListOf()
    var optionalDiscountTemp : OptionalDiscount = OptionalDiscount.FIJO
    var discountAmountTemp: Double = 0.0
    var enabledTemp : Boolean = false


    var selectedProductL : ProductModel? = null
    var selectedProductR : ProductModel? = null
    constructor():this(0,"","", mutableListOf(),OptionalDiscount.FIJO,0.0,false)
    init {
        menu = Menu( name,description,products,optionalDiscount,discountAmount,enabled)

    }



    fun totalPrice() : Double{
        return menu.totalPrice()
    }

    fun changeList( pm : ProductModel?, origin : MutableList<ProductModel> , destiny : MutableList<ProductModel>){
        if (pm != null) {
            origin.remove(pm)
            destiny.add(pm)
        }
    }

    fun syncMenu(){
        menu = Menu( name,description,products,optionalDiscount,discountAmount,enabled)
    }


    fun menuToMenuModel(menu : Menu) : MenuModel{

        return MenuModel(menu.code,menu.name,menu.description,menu.products,menu.optionalDiscount,menu.discountAmount,menu.enabled)
    }

    fun initMenuModif(){
        codeTemp = code
        nameTemp = name
        descriptionTemp = description
        productsTemp  = products
        optionalDiscountTemp  = optionalDiscount
        discountAmountTemp = discountAmount
        enabledTemp = enabled

    }



}