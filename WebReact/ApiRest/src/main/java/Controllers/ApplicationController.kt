package Controllers
import PaymentMethods
import Restaurant
import Application
import Customer
import PerfectMatch
import PartialMatch
import Searchable
import Deliver
import Rating
import RestaurantWithoutMenu
import Order
import java.time.LocalDate
import Search
import MenuID
import Menu
object ApplicationController {
    var app : Application? = null
    var delivers : MutableMap<Deliver,Rating> = mutableMapOf()
    fun createApplication ( restaurants: MutableList<Restaurant> ,  pMethods : MutableList<PaymentMethods>){
        app = Application(restaurants, pMethods)
    }

    fun getUser(id : Int) : Customer? {
        return app!!.getCustomerWhitId(id)
    }

    fun getRestaurant(id : Int) : Restaurant?{
        return app!!.getRestaurantWhitId(id)
    }

    fun getRestaurantByName(name : String) : Restaurant?{
       val search =  PerfectMatch()
       return search.searchRestaurant(name,app!!.restaurants)
    }

    fun getRestaurantsByName(name : String) : MutableList<Restaurant>{
        val search = PartialMatch()
        return search.searchRestaurant(name,app!!.restaurants)
        //val menu  = result.find { elem -> elem is Menu }
    }

    fun getMenusByName(name: String) : MutableList<Searchable>{
        return app!!.restaurants.flatMap{ rest -> rest.menus.filter { menu -> menu.name.toLowerCase().contains(name.toLowerCase()) }}.toMutableList()
    }

    fun withLocation(rests : MutableList<Restaurant>, lat : Double, long : Double) : Restaurant?{

        return rests.find { elem -> elem.location.latitude == lat && elem.location.longitude == long }
    }

    fun registerCustomer(customer:Customer){
        app!!.newCustomer(customer.userName,customer.email,customer.password,customer.address,customer.coord.latitude,customer.coord.longitude)

    }

    fun loginUser(username: String, password: String): Customer{
        return app!!.connectCustomer(username, password)
    }

    fun addDeliver(cuerpo: Deliver, rating: Rating) {
        delivers.put(cuerpo,rating)
    }

    fun addOrderToUser(body : Deliver){

       val user = app!!.customers.find {elem -> body.customerName == elem.userName }

       val menus = body.menus.flatMap { elem -> Search(app!!).MenuByCode(elem.menuId) }.toMutableList()


       val rest = Search(app!!).RestByCode(body.restaurantId)
       val order = Order(LocalDate.now(),user!!,rest!!,menus,State.PENDING,body.paymentMethod)
        body.code = order.code
        body.order = order

    }
    fun putRatingInDelivery(id: Int, rating: Rating) {
        val getDelivery  = getDeliver(id)
        delivers.set(getDelivery!!,rating)
    }

    fun getDeliver(id : Int) : Deliver?{
       return  delivers.keys.find { elem -> elem.code == id }
    }

    fun getRating(deliver : Deliver) : Rating?{
        return delivers.get(deliver)
    }
    fun restsToRestWithoutMenu(rest : MutableList<Restaurant>) : MutableList<RestaurantWithoutMenu>{
        return rest.map{elem -> RestaurantWithoutMenu(elem.code,elem.name,elem.description,elem.dir,elem.location,elem.pMethods)}.toMutableList()
    }

    fun getMenu(id:Int):Restaurant{
        return app!!.restaurants.find{restaurant -> restaurant.menus.any{ menu -> menu.code == id }}!!
    }

}