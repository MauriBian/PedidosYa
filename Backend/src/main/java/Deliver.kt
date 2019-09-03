import geoclase.Geo
import java.time.LocalDate

class Deliver (val customerName : String,val restaurantId : Int, val menus : MutableList<MenuID>, val paymentMethod: PaymentMethods, val destination : Geo) {
    var order : Order ? = null
    var code : Int ? = null
}

class MenuID (val menuId : Int , val amount : Int){

}
