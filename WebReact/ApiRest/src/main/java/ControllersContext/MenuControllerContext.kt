package ControllersContext

import Controllers.ApplicationController
import Restaurant
import Searchable
import io.javalin.Context

class MenuControllerContext {

    fun getMenu(ctx : Context){

        val id = ctx.pathParam("id").toInt()
        val menu = ApplicationController.getMenu(id)!!
        ctx.json(menu)
    }
}
