import geoclase.Geo

class Restaurant (override val name: String, val description : String, val dir: String, val location: Geo, val pMethods : MutableList<PaymentMethods>, var menus: MutableList<Menu>) : Searchable {
val code = CodeGenerator.newCode()


    fun getMenuAndRest(menu :Menu) : Pair<Menu,Restaurant>{


        return Pair(menu,this)


    }


    fun getProducts() : MutableList<Product>{
        var res : MutableList<Product> = mutableListOf()
        menus.map{menu -> menu.products.map{product -> if (!res.contains(product)){res.add(product)}}}
        return res
    }



}