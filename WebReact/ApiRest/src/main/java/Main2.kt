
import Controllers.ApplicationController
import ControllersContext.CustomerControllerContext
import io.javalin.Javalin
import io.javalin.apibuilder.ApiBuilder.*
import org.eclipse.jetty.http.HttpStatus.*
import com.fasterxml.jackson.databind.exc.MismatchedInputException
import Bootstrap
import ControllersContext.DeliverControllerContext
import ControllersContext.RestaurantControllerContext
import ControllersContext.MenuControllerContext

fun main() {
    val app = Javalin.create()
            .enableCorsForAllOrigins()
            .enableRouteOverview("/routes")
            .exception(MismatchedInputException :: class.java){ e , ctx ->
                ctx.status(BAD_REQUEST_400)
                ctx.json(mapOf(
                        "status" to BAD_REQUEST_400,
                        "message" to e.message
                ))
            }
            .start(7000)

    val b = Bootstrap()
    ApplicationController.app = b.application
    val customerController = CustomerControllerContext()
    val deliverController = DeliverControllerContext()
    val restaurantController = RestaurantControllerContext()
    val menuController = MenuControllerContext()

    app.exception(Exception::class.java) { e, ctx ->
        ctx.result(e.message!!)
        ctx.status(401)
    }

    app.exception(NullPointerException::class.java) { e, ctx ->
        ctx.result("No se encontro algo con ese ID")
        ctx.status(404)
    }

    app.routes{
        path("register"){
            post(customerController :: register)
        }
        path("login"){
            post(customerController :: login)
        }
        path("users"){
            path(":id"){
                get(customerController :: getCustomerOrders)
            }
            path(":id?include=orders"){
                get(customerController :: getCustomerOrders)
            }
        }
        path("deliver"){
            post(deliverController :: addDeliver)
            path(":id"){
                put(deliverController :: putDeliver)

            }
            path(":id"){
                get(deliverController :: getDeliver)
            }
        }
        path("search"){
            get(restaurantController :: searchRestaurant)
        }
        path("restaurant"){
            path(":id"){
                get(restaurantController :: getRestaurant)
            }
        }
        path("menu"){
            path(":id"){
                get(menuController :: getMenu)
            }
        }

    }
}