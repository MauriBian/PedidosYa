


import geoclase.Geo
import Customer
import Menu
import Order
import java.time.LocalDate
import SuperUser
import Restaurant
import Product
import PaymentMethods
import org.junit.Before
import org.junit.Test
import java.util.*
import kotlin.collections.ArrayList
import Application
import kotlin.test.assertEquals

class UserTest {

    val sausages = Product( "sausages with mashed", "very delicious", 30.0, Category.PLATO_PRINCIPAL)
    var productList = mutableListOf<Product>(sausages)
    val geo : Geo = Geo(12.2,12.3,"casa")
    val menu: Menu= Menu("menu","descripcion",productList, OptionalDiscount.FIJO,10.0,true)
    val menus :MutableList<Menu> = mutableListOf(menu)
    val mp = mutableListOf<PaymentMethods>(PaymentMethods.Cash,PaymentMethods.CreditCard)
    val superUser : SuperUser = SuperUser("pedro","gonzales")
    val pato : Restaurant = Restaurant("Pato","Ponele cheddar a todo", "Av. 3 1150", geo, mp,menus)
    val user : Customer = Customer("ricardo", "mati@hola","ricardo", "rodo 523", geo)
    val orders : MutableList<Order> = mutableListOf()
    val order1 : Order = Order( LocalDate.of(2004,12,12),user,pato,menus,State.DELIVERED,mp.first())
    val customer = Customer("usuario","mati@hola", "usuario","rodo 523",geo)

    @Test fun CreateNewCustomer(){
        assertEquals(customer.userName,"usuario")
        assertEquals(customer.password,"usuario")
        assertEquals(customer.address,"rodo 523")
        assertEquals(customer.coord,geo)
        assertEquals(customer.regisDate, LocalDate.now())
        customer.orders.add(order1)
        assertEquals(customer.orders[0].code,order1.code)
    }

    @Test fun Probando(){
        val partialmatch: PartialMatch = PartialMatch()
        val lista : MutableList<Restaurant> = mutableListOf()
        lista.add(pato)
        pato.menus.add(menu)
        assertEquals(partialmatch.searchMenuInAllRestaurants("Menu",lista), mutableListOf())

    }

    @Test
    fun getOrdersLog(){
        customer.orders.add(order1)
        assertEquals(customer.getOrdersLog().size,1)
    }
}