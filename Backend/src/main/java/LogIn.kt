import exceptions.ExceptionSignup

class LogIn {

    fun connectSuperUser(userName: String, password: String, userList: MutableList<SuperUser>): SuperUser {

        val user = userList.find { user -> user.userName == userName && user.password == password }

        if (user != null) {
            return user
        } else {
            throw ExceptionSignup("Usuario o contraseña incorrectos")
        }

    }

    fun connectCustomer(userName : String , password : String , userList: MutableList<Customer>) : Customer {
        val customer = userList.find { cust -> cust.userName == userName && cust.password == password }

        if (customer != null){
            return customer
        }
        else{
            throw ExceptionSignup("Usuario o contraseña incorrectos")
        }
    }
}

