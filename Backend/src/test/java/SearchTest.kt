
import geoclase.Geo
import org.junit.Test
import kotlin.test.assertEquals

class SearchTest {
    val mp = mutableListOf<PaymentMethods>(PaymentMethods.Cash,PaymentMethods.CreditCard)
    val loc : Geo = Geo(12441.0,12414.0)
    val su : SuperUser = SuperUser("Juan","123")

    var products = mutableListOf<Product>()
    val menu: Menu = Menu("menu","descripcion",products, OptionalDiscount.FIJO,10.0,true)
    val menus :MutableList<Menu> = mutableListOf(menu)
    val resto1 : Restaurant = Restaurant("Lo de Pato","Parrilla al paso","Rodo 523",loc,mp,menus)
    val resto2 : Restaurant = Restaurant("El Buen Comer","Tenedor Libre","Calle falsa 123",loc,mp,menus)
    val resto3 : Restaurant = Restaurant("Beto Burger","Hamburgueseria de primera","Monteagudo 521",loc,mp,menus)
    var list : MutableList<Restaurant> = mutableListOf(resto1,resto2,resto3)
    val user1 : MutableList<User> = mutableListOf(SuperUser("juan","123"))
    val search : Search = Search(Application(list,mp))

    @Test
    fun RestByCode( ){
        val resto : Restaurant? = search.RestByCode(resto2.code)
        assertEquals(resto2,resto)
     }
//
//    fun RestByName (restName : String) :Restaurant{
//
//    }
//
//    fun RestByCodeOrName (rest : String) : Restaurant {
//
//    }
//
//    fun MenuInAllRestaurants( menuName : String) : Menu {
//
//    }
//
//    fun MenuInRestaurant( menuName : String) : Menu {
//
//    }
//
//    fun MenuOrRestaurant( name : String)  {
//
//    }


}