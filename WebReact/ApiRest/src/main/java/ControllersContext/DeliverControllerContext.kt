package ControllersContext

import Controllers.ApplicationController
import io.javalin.Context
import Deliver
import Rating

class DeliverControllerContext{

    fun addDeliver(ctx : Context){
        val deliver = ctx.body<Deliver>()
        val rating = Rating(0)
        ApplicationController.addDeliver(deliver, rating)
        ApplicationController.addOrderToUser(deliver)
        ctx.result("OK")
        ctx.status(202)
    }

    fun putDeliver(ctx: Context){
        val id = ctx.pathParam("id").toInt()
        val rating = ctx.body<Rating>()
        ApplicationController.putRatingInDelivery(id,rating)
        ctx.result("Calificado")
    }

    fun getDeliver(ctx: Context){
       val id = ctx.pathParam("id").toInt()
        val deliver = ApplicationController.getDeliver(id)!!
        ctx.json(Pair<Deliver,Rating>(deliver,ApplicationController.getRating(deliver)!!))
        ctx.status(200)
    }

}