package ControllersContext

import Controllers.ApplicationController
import Restaurant
import Searchable
import io.javalin.Context

class RestaurantControllerContext {

    fun getRestaurant(ctx : Context){

        val id = ctx.pathParam("id").toInt()
        val restaurant = ApplicationController.getRestaurant(id)!!
        ctx.json(restaurant)
    }

    fun searchRestaurant(ctx: Context){

        val paramName = ctx.queryParam("q")!!
        var restaurants = ApplicationController.getRestaurantsByName(paramName)
        var restaurant : Restaurant? = null
        if (!ctx.anyQueryParamNull("lat","long")){
            val lat = ctx.queryParam("lat")
            val long = ctx.queryParam("long")
            restaurant = ApplicationController.withLocation(restaurants,lat!!.toDouble(),long!!.toDouble())
        }

        val menus : MutableList<Searchable> = ApplicationController.getMenusByName(paramName)
        if (restaurant != null){
            restaurants = mutableListOf(restaurant)
        }
        else if(!ctx.anyQueryParamNull("lat","long")){
            restaurants = mutableListOf()
        }

        val searchable = ApplicationController.restsToRestWithoutMenu(restaurants) as MutableList<Searchable>
        val  mapMenuYRest : Map<String,MutableList<Searchable>> = mapOf("Restaurants" to searchable,"Menus" to menus)
        ctx.json(mapMenuYRest)
    }

}
