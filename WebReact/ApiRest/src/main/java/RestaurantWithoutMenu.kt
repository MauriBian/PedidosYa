import geoclase.Geo

class RestaurantWithoutMenu(val code : Int, override val name : String, val description : String, dir : String, location : Geo, val paymentMethods:MutableList<PaymentMethods>) : Searchable {

}
