import exceptions.ExceptionOrder
import geoclase.GeoCalculator
import java.time.LocalDate


class Order( val day: LocalDate, val user: Customer, val restaurant: Restaurant, val menus: MutableList<Menu>, val state: State, val paymentMethod : PaymentMethods) {
    val code : Int = CodeGenerator.newCode()
    init {
    verifyConditions()

 }


    private fun verifyConditions(){
        if (restaurant.pMethods.contains(this.paymentMethod)// contiene los medios de pago
                        && GeoCalculator.distance(user.coord,restaurant.location)  <= 20.0// si esta a una distanca menor a 20 km
                        && !menus.isEmpty()// si no esta vacia
                        &&  menus.all{menu -> restaurant.menus.contains(menu)})// no hay ningun menu que pertenesca a otro restaurante
        {
            user.orders.add(this)
        }else{
            throw ExceptionOrder("No se pudo crear la orden")
        }
    }
}

enum class State{DELIVERED,PENDING,ARRIVING,CANCELLED}
