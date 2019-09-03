package Controllers

import java.time.LocalDate
import Restaurant
import Menu
import State
import PaymentMethods
import geoclase.Geo

data class OrderWithoutUser(val code : Int, val day: LocalDate, val restaurant: Restaurant, val menus: MutableList<Menu>, val state: State, val paymentMethod : PaymentMethods)

data class CustomerWithFixOrders(val code : Int, val userName : String, val email: String, val password : String, val address : String, val coord : Geo, val regisDate : LocalDate, val orders : MutableList<OrderWithoutUser>)