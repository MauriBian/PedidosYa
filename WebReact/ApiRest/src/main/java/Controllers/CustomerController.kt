package Controllers

import geoclase.Geo
import Customer

class CustomerController (val username : String, val email : String, val password : String , val address : String, val coord : Geo){
    var customer : Customer? = null
    init {
        customer = Customer(username,email,password,address,coord)
        ApplicationController.app!!.customers.add(customer!!)
    }
}

