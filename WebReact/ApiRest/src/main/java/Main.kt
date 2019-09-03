import Controllers.ApplicationController
import Controllers.CustomerSinORder
import io.javalin.Javalin
import LoginData

fun main(args: Array<String>) {

    val b = Bootstrap()
    ApplicationController.app = b.application

    val app = Javalin.create()
            .enableCorsForAllOrigins()
            .start(7000)

    app.exception(Exception::class.java) { e, ctx ->
        // handle general exceptions here
        // will not trigger if more specific exception-mapper found
        ctx.result(e.message!!)
        ctx.status(401)
    }


    app.exception(NullPointerException::class.java) { e, ctx ->
        ctx.result("No se encontro algo con ese ID")
        ctx.status(404)
    }



    app.post( "/register") {  ctx ->
        val asd = ctx.body<Customer>()
        ApplicationController.app!!.customers.add(asd)
        ctx.result("OK")
        ctx.status (201)
    }

    app.post ("/login") { ctx ->
        val cuerpo = ctx.body<LoginData>()
        val user = ApplicationController.app!!.connectCustomer(cuerpo.username, cuerpo.password)
        ctx.json(user)
        ctx.status(202)
    }

    app.get("/users/:id") {ctx ->

        val id = ctx.pathParam("id").toInt()

        val cc = ApplicationController.getUser(id)!!

        if (ctx.anyQueryParamNull("include")){
            ctx.json(CustomerSinORder(cc.userName,"",cc.password,cc.address,cc.coord))
        }else{
            ctx.json(cc)
        }

    }

    app.get("/users/:id?include=orders") {ctx ->
        val id = ctx.pathParam("id").toInt()
        ctx.json(ApplicationController.getUser(id)!!)

    }

    app.post ("/deliver") {ctx ->
        val cuerpo = ctx.body<Deliver>()
        ApplicationController.delivers.put(cuerpo,Rating(0))
        ctx.result("OK")
        ctx.status(202)
    }

    app.put("/deliver/:id"){ctx ->
        val id = ctx.pathParam("id").toInt()
        val body = ctx.body<Rating>()
        val getDelivery  = ApplicationController.delivers.keys.elementAt(id)
        ApplicationController.delivers.set(getDelivery,body)

        ctx.result("Calificado")

    }

    app.get ("search") { ctx ->

        val q = ctx.queryParam("q")!!
        var cc = ApplicationController.getRestaurantsByName(q)
        var ccfilter : Restaurant? = null
        if (!ctx.anyQueryParamNull("lat","long")){
            val lat = ctx.queryParam("lat")
            val long = ctx.queryParam("long")
            ccfilter = ApplicationController.withLocation(cc,lat!!.toDouble(),long!!.toDouble())
        }


        val ccMenu : MutableList<Searchable> = ApplicationController.getMenusByName(q)
        if (ccfilter != null){
             cc = mutableListOf(ccfilter)
        }
        else if(!ctx.anyQueryParamNull("lat","long")){
            cc = mutableListOf()
        }

        val searchable = cc as MutableList<Searchable>
        val  mapMenuYRest : Map<String,MutableList<Searchable>> = mapOf("Restaurants" to searchable,"Menus" to ccMenu)
        ctx.json(mapMenuYRest)

    }


    app.get ("restaurant/:id"){ ctx ->

        val id = ctx.pathParam("id").toInt()

        val cc = ApplicationController.getRestaurant(id)!!

        ctx.json(cc)


    }









}
