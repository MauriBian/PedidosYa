
import exceptions.ExceptionFind
import exceptions.ExceptionSignup
import geoclase.Geo

enum class PaymentMethods {
    Cash, CreditCard, MercadoPago
}
class Application (val restaurants : MutableList<Restaurant>, val pMethods : MutableList<PaymentMethods>) {

    val customers = mutableListOf<Customer>()
    val superUsers = mutableListOf<SuperUser>()

    fun newCustomer(name: String, email: String, password: String, address : String ,latitude: Double , longitude: Double ){
        var geo: Geo = Geo(latitude, longitude, address)
        var newCustomer: Customer = Customer(name,email, password, address, geo)
        if(customers.any{cust -> cust.userName == newCustomer.userName}){
            throw ExceptionSignup("El usuario ya existe")
        }else{
            customers.add(newCustomer)
        }
    }

    fun newSuperUser(name: String, password: String ){
        var user: SuperUser = SuperUser(name, password)
        if(superUsers.any{superuser -> superuser.userName == name}){
            throw ExceptionSignup("El usuario ya existe")
        }else{
            superUsers.add(user)
        }
    }

    fun connectSuperUser(userName : String , password: String ) : SuperUser{
        val log = LogIn()
        return log.connectSuperUser(userName,password,superUsers)
    }

    fun connectCustomer(userName : String , password : String) : Customer{
        val log = LogIn()
        return log.connectCustomer(userName,password,customers)
    }

    fun getCustomerWhitId(id:Int): Customer? {
        val customer = customers.find { elem -> elem.code == id }
        if (customer != null) {
            return customer
        }else{
            throw ExceptionFind("No existe un usuario con la id = " + id)
        }
    }

    fun getRestaurantWhitId(id: Int): Restaurant? {
        val restaurant = restaurants.find{ restaurant -> restaurant.code == id }
        if (restaurant != null) {
            return restaurant
        }else{
            throw ExceptionFind("no existe un restaurante con la id = " + id)
        }
    }
}

