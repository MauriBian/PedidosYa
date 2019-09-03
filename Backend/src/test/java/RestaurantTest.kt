import geoclase.Geo
import junit.framework.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito
import kotlin.test.assertEquals
class RestaurantTest {

    val loc : Geo = Geo(151551.0,1515.0)
    val superUser : SuperUser = Mockito.mock(SuperUser::class.java)
    val su  :SuperUser =  SuperUser("usuario","contraseña")

    val mp = mutableListOf<PaymentMethods>(PaymentMethods.Cash,PaymentMethods.CreditCard)
    var products = mutableListOf<Product>()
    val menu: Menu = Menu("menu","descripcion",products, OptionalDiscount.FIJO,10.0,true)
    val menus :MutableList<Menu> = mutableListOf(menu)
    val pato : Restaurant = Restaurant("Pato","Ponele cheddar a todo", "Av. 3 1150", loc, mp,menus)


    @Test
    internal fun createRestaurantAndGetName( ){
//       val pato : Restaurant = Restaurant(1,"Pato","Ponele cheddar a todo", su, "Av. 3 1150", loc, mp)
       // assertEquals(pato.name , "Pato")
    }

    @Test
    internal fun createRestaurantAndGetLocation( ){
        val pato : Restaurant = Restaurant("Pato","Ponele cheddar a todo",  "Av. 3 1150", loc, mp,menus)
        assertEquals(pato.location , loc)
    }

}
