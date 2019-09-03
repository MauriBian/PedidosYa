
import org.uqbar.commons.model.annotations.Observable
import org.uqbar.commons.model.exceptions.UserException

@Observable
object ApplicationModel {
    val b = Bootstrap()
    val application: Application = b.application

    init {
        application.customers.addAll(b.customers)
    }

    var user = "pedro"
    var password = "123"


    var products = mutableListOf<ProductModel>()

    var selectedProduct: ProductModel? = null
    var menus = mutableListOf<MenuModel>()
    var selectedMenu: MenuModel? = null

    var selectedRestaurant: Restaurant? = null

    var searcherL: String = ""
    var searcherR: String = ""
    fun autenticar() {
        selectedRestaurant = application.connectSuperUser(user, password).restaurant
        menus = selectedRestaurant!!.menus.map { elem -> MenuModel().menuToMenuModel(elem) }.toMutableList()
        products = selectedRestaurant!!.getProducts().map { elem -> ProductModel().prodToProdModel(elem) }.toMutableList()
        selectedMenu = menus[0]
        selectedProduct = products[0]
    }

    fun deleteMenuSelected() {
            println(menus.map { menu -> menu.name })
            menus.remove(selectedMenu!!)
            selectedRestaurant!!.menus = selectedRestaurant!!.menus.filter { menu -> menu.name != selectedMenu!!.name }.toMutableList()
            selectedMenu = null
            println(menus.map { menu -> menu.name })
    }

    fun deleteProductSelected() {
        if(productInMenus(this.selectedProduct!!.name)){
            throw UserException("Error: un menu esta usando ese producto")
        }
        else{
            products.remove(selectedProduct!!)
        }
    }

    fun productInMenus(selectedProduct: String):Boolean{
        return selectedRestaurant!!.menus.any{menu -> menu.products.any { product-> product.name == selectedProduct}}
    }

    fun makeSearchProducts() {
        products = Search(application).parcialSearch.searchProductsByName(searcherL,selectedRestaurant!!.getProducts()).map{ x -> ProductModel().productToProductModel(x)}.toMutableList()
    }

    fun makeSearchMenus(){
        menus = Search(application).parcialSearch.searchMenuInRestaurant(searcherR,selectedRestaurant!!).map { x -> MenuModel().menuToMenuModel(x) }.toMutableList()
    }



}