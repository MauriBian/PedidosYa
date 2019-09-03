import geoclase.Geo
import java.time.LocalDate



class Bootstrap {
    val sausages = Product( "sausages with mashed", "very delicious", 30.0,Category.PLATO_PRINCIPAL)
    val burger = Product("burger", "very delicious", 40.5, Category.PLATO_PRINCIPAL)
    val tomato = Product( "Tomato", "A simple tomato", 5.5, Category.ENTRADA)
    val pepsi = Product( "Pepsi", "a delicious drink", 7.5, Category.BEBIDA)
    var productList = mutableListOf<Product>(sausages ,burger , tomato  , pepsi )

    var productList2 =mutableListOf<Product>(sausages , pepsi )
    var productList3 = mutableListOf<Product>(burger , tomato)

    val geo : Geo = Geo(12.2,12.3,"casa")
    val menu: Menu= Menu("menu","descripcion",productList, OptionalDiscount.FIJO,10.0,true)
    val menu2: Menu= Menu("menu2","descripcion2",productList2, OptionalDiscount.FIJO,10.0,true)
    val menu3: Menu= Menu("menu3","descripcion3",productList3, OptionalDiscount.FIJO,10.0,true)
    val menus :MutableList<Menu> = mutableListOf(menu,menu2,menu3)
    val mp = mutableListOf<PaymentMethods>(PaymentMethods.Cash,PaymentMethods.CreditCard)
    val superUser : SuperUser = SuperUser("pedro","123")
    val superUserPato = SuperUser("pato","1234")
    val beto :Restaurant= Restaurant("Beto Burger","hamburguesas","monteagudo 2300",geo,mp,menus)

    val cheddar = Product( "cheddar", "cheddar caliente", 5.0, Category.PLATO_PRINCIPAL)
    val pancho = Product("Salchicha", "salchicha vienessa", 10.0, Category.PLATO_PRINCIPAL)
    val milanesa = Product( "milanesa", "Mila de pollo", 13.0, Category.PLATO_PRINCIPAL)

    var productList4 = mutableListOf<Product>(pancho,cheddar)
    var productList5 = mutableListOf<Product>(burger,cheddar)
    var productList6 = mutableListOf<Product>(milanesa,cheddar)

    val menu4: Menu= Menu("Pancho Cheddar","panchito con cheddar",productList4, OptionalDiscount.FIJO,10.0,true)
    val menu5: Menu= Menu("Hamburguesa Cheddar","simil cuarto de libra",productList5, OptionalDiscount.FIJO,10.0,true)
    val menu6: Menu= Menu("Milanesa Cheddar","milanesa de pollo con cheddar",productList6, OptionalDiscount.FIJO,10.0,true)
    val menus2 :MutableList<Menu> = mutableListOf(menu4,menu5,menu6)

    val pato : Restaurant = Restaurant("Pato","Ponele cheddar a todo", "Av. 3 1150", geo, mp,menus2)
    val ricardo : Customer = Customer("ricardo", "ricardo@gmail.com","123" ,"rodo 523",geo)
    val orders : MutableList<Order> = mutableListOf()
//    val order1 : Order = Order(1, LocalDate.of(2004,12,12),ricardo,pato,menus,State.DELIVERED,mp.first())
    val ortigotze = Customer("nestor","ortigoza@gmail.com","nestor","rodo 523",geo)
    val customers = mutableListOf<Customer>(ricardo,ortigotze)
    val rests = mutableListOf<Restaurant>(beto,pato)
    val application = Application(rests,mp)
   init {

       application.superUsers.add(superUser)
       application.superUsers.add(superUserPato)
       superUser.restaurant = beto
       superUserPato.restaurant = pato
   }



}