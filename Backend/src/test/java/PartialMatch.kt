import geoclase.Geo
import org.junit.Test
import org.mockito.Mockito
import kotlin.test.assertEquals

class PartialMatchTest {
    @Test
    fun testSearchAMenuInRestaurant(){
        val pMatch  = PartialMatch()
        val loc : Geo = Geo(151551.0,1515.0)
        val superUser : SuperUser = Mockito.mock(SuperUser::class.java)
        val mp = mutableListOf<PaymentMethods>(PaymentMethods.Cash,PaymentMethods.CreditCard)
        var products = mutableListOf<Product>()
        val menu: Menu = Menu("tomato soup","descripcion",products, OptionalDiscount.FIJO,10.0,true)
        val menu2: Menu = Menu("meat with salad","descripcion",products, OptionalDiscount.FIJO,10.0,true)
        val menu3: Menu = Menu("potato and tomato","descripcion",products, OptionalDiscount.FIJO,10.0,true)
        val menus :MutableList<Menu> = mutableListOf(menu,menu2,menu3)
        val pato : Restaurant = Restaurant("Pato","Ponele cheddar a todo",  "Av. 3 1150", loc, mp,menus)
        val menusRes : MutableList<Menu>? = pMatch.searchMenuInRestaurant("soup",pato)
        assertEquals(menusRes?.size,1)
        //assertEquals(menusRes?.get(0)?.code,31)

    }

    @Test
    fun testSearchAMenuInRestaurants(){
        val pMatch = PartialMatch()
        val loc : Geo = Geo(151551.0,1515.0)
        val superUser : SuperUser = Mockito.mock(SuperUser::class.java)
        val mp = mutableListOf<PaymentMethods>(PaymentMethods.Cash,PaymentMethods.CreditCard)
        var products = mutableListOf<Product>()
        val menu: Menu = Menu("tomato soup","descripcion",products, OptionalDiscount.FIJO,10.0,true)
        val menu2: Menu = Menu("meat with salad","descripcion",products, OptionalDiscount.FIJO,10.0,true)
        val menu3: Menu = Menu("potato and tomato","descripcion",products, OptionalDiscount.FIJO,10.0,true)
        val menus :MutableList<Menu> = mutableListOf(menu,menu2)
        val menus2 :MutableList<Menu> = mutableListOf(menu3)
        val pato : Restaurant = Restaurant("Pato","Ponele cheddar a todo",  "Av. 3 1150", loc, mp,menus)
        val pato2 : Restaurant = Restaurant("Pato2","Ponele cheddar a todo2",  "Av. 3 1151", loc, mp,menus2)
        val restarants :MutableList<Restaurant> = mutableListOf(pato,pato2)
        val menusRes : MutableList<Pair<Menu,Restaurant>> = pMatch.searchMenuInAllRestaurants("tomato",restarants)
        //assertEquals(menusRes.size,2)
        //assertEquals(menusRes.get(0).second,pato)

    }
}