import exceptions.ExceptionOrder
import geoclase.Geo
import org.junit.Test
import java.time.LocalDate
import kotlin.test.assertEquals

class OrderTest {
    val mp = mutableListOf<PaymentMethods>(PaymentMethods.Cash,PaymentMethods.CreditCard)
    val geo : Geo = Geo(12.2,12.3,"casa")
    val sausages = Product( "sausages with mashed", "very delicious", 30.0, Category.PLATO_PRINCIPAL)
    var productList = mutableListOf<Product>(sausages)
    val menu: Menu= Menu("menu","descripcion",productList, OptionalDiscount.FIJO,10.0,true)
    val menus :MutableList<Menu> = mutableListOf(menu)
    val superUser : SuperUser = SuperUser("pedro","gonzales")
    val pato : Restaurant = Restaurant("Pato","Ponele cheddar a todo",  "Av. 3 1150", geo, mp,menus)
    val user : Customer = Customer("ricardo", "ad","ricardo", "rodo 523", geo)



    @Test fun testICanPlaceAnOrder(){

        val order = Order( LocalDate.of(2000,12,13), user, pato,menus, State.DELIVERED,PaymentMethods.Cash)
      //  assertEquals(order.code,17)
        assertEquals(order.day,LocalDate.of(2000,12,13))
        assertEquals(order.user.userName,"ricardo")
       // assertEquals(order.restaurant.code,16)
        //assertEquals(order.menus[0].code,15)
        //assertEquals(order.state,State.DELIVERED)
    }

    @Test(expected = ExceptionOrder::class)
    fun testICantPlaceAnOrderIfTheRestaurantDoesNotHavePaymentMethod(){
        val invalidMP = PaymentMethods.MercadoPago
        val order = Order( LocalDate.of(2000,12,13), user, pato,menus, State.DELIVERED,invalidMP)
    }

    @Test(expected = ExceptionOrder::class)
    fun testICantPlaceAnOrderIfItIsMoreThan20KmAway(){
        val geo2 : Geo = Geo(1.0,1.0,"casa")
        val distantUser : Customer = Customer("ricardo", "asd","ricardo", "rodo 523", geo2)
        val order = Order( LocalDate.of(2000,12,13), distantUser, pato,menus, State.DELIVERED,PaymentMethods.Cash)
    }

    @Test(expected = ExceptionOrder::class)
    fun testICantPlaceAnOrderWhitoutAMenus(){
        val withoutMenus :MutableList<Menu> = mutableListOf()
        val order = Order( LocalDate.of(2000,12,13), user, pato,withoutMenus, State.DELIVERED,PaymentMethods.Cash)
    }

    @Test(expected = ExceptionOrder::class)
    fun testICantPlaceAnOrderIfTheMenuDoesNotBelongToTheRestaurant(){
        val invalidMenu: Menu= Menu("menuu","des",productList, OptionalDiscount.PORCENTUAL,3.0,true)
        val invalidMenus :MutableList<Menu> = mutableListOf(menu,invalidMenu)
        val order = Order( LocalDate.of(2000,12,13), user, pato,invalidMenus, State.DELIVERED,PaymentMethods.Cash)
    }





}