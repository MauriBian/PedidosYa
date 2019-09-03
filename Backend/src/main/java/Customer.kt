import geoclase.Geo
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalTime
import java.util.*

open class Customer (userName : String, val email: String,  password : String, var address : String, var coord : Geo ) : User(userName,password){
      val code = CodeGenerator.newCode()
      val regisDate :LocalDate = LocalDate.now()
      val orders = mutableListOf<Order>()

      fun getOrdersLog() : MutableList<Order>{
            return orders
      }
}