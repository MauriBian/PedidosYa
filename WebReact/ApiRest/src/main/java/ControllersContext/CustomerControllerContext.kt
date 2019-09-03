package ControllersContext

import Controllers.ApplicationController
import Controllers.CustomerSinORder
import Controllers.CustomerWithFixOrders
import Controllers.OrderWithoutUser
import io.javalin.Context
import Customer
import LoginData
import Order

class CustomerControllerContext{

    fun register(ctx: Context){
        val customer = ctx.body<Customer>()
        ApplicationController.registerCustomer(customer)
        ctx.result("ok")
        ctx.status (201)
    }

    fun login(ctx: Context){
        val cuerpo = ctx.body<LoginData>()
        val customer = ApplicationController.loginUser(cuerpo.username, cuerpo.password)
        ctx.json(CustomerWithFixOrders(customer.code,customer.userName,customer.email,customer.password,customer.address,customer.coord,customer.regisDate, ordersWithoutUsers(customer.orders)))
        ctx.status(202)
    }

    fun getCustomer(ctx: Context){
        val id = ctx.pathParam("id").toInt()
        ctx.json(ApplicationController.getUser(id)!!)
    }

    fun getCustomerOrders(ctx: Context){
        val id = ctx.pathParam("id").toInt()
        val customer = ApplicationController.getUser(id)!!
        if (ctx.anyQueryParamNull("include")){
            ctx.json(CustomerSinORder(customer.userName,customer.email,customer.password,customer.address,customer.coord))
        }else{

            ctx.json(CustomerWithFixOrders(customer.code,customer.userName,customer.email,customer.password,customer.address,customer.coord,customer.regisDate, ordersWithoutUsers(customer.orders)))
        }
    }

    fun ordersWithoutUsers(orders : MutableList<Order>) : MutableList<OrderWithoutUser>{

        return orders.map { elem -> OrderWithoutUser(elem.code,elem.day,elem.restaurant,elem.menus,elem.state,elem.paymentMethod)}.toMutableList()
    }

}
