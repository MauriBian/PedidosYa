import org.junit.Test
import geoclase.Geo
import kotlin.test.assertEquals

class PerfectMatchTest {
    val pMatch : PerfectMatch = PerfectMatch()
    val loc : Geo = Geo(12441.0,12414.0)
    val mp = mutableListOf<PaymentMethods>(PaymentMethods.Cash,PaymentMethods.CreditCard)
    var products = mutableListOf<Product>()
    val su : SuperUser = SuperUser("Juan","123")

    val menu: Menu = Menu("menu","descripcion",products, OptionalDiscount.FIJO,10.0,true)
    val menus :MutableList<Menu> = mutableListOf(menu)
    val resto1 : Restaurant = Restaurant("Lo de Pato","Parrilla al paso","Rodo 523",loc,mp,menus)
    val resto2 : Restaurant = Restaurant("El Buen Comer","Tenedor Libre","Calle falsa 123",loc,mp,menus)
    val resto3 : Restaurant = Restaurant("Beto Burger","Hamburgueseria de primera","Monteagudo 521",loc,mp,menus)

    @Test
    internal fun testSearchRestaurantByCode() {
        val restoList : MutableList<Restaurant> = mutableListOf(resto1,resto2,resto3)
        val result : Restaurant? = pMatch.searchRestaurant(resto1.code.toString(),restoList)
        assertEquals(result?.code ,resto1.code)
        //assertEquals(resto1 ,result)

    }



}