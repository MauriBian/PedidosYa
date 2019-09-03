import org.uqbar.arena.windows.Window

//import org.uqbar.arena.Application

class Main : org.uqbar.arena.Application() {
//    override fun createMainWindow(): InformationWindow {
//        val x = InformationWindow(this, ApplicationModel() )
//        var y = mutableListOf("menu 1","menu 2","menu 3","menu 4","menu 5","menu 5","menu 6","menu 7","menu 8")
//        x.setValues("menus que tienen milanesa",y,"")
//        return x
//    }

    override fun createMainWindow(): LoginWindow{
        val sausages = Product( "sausages with mashed", "very delicious", 30.0, Category.PLATO_PRINCIPAL)
        var productList = mutableMapOf<Product,Double>((sausages to 1.0))

        return LoginWindow(this,ApplicationModel)
    }




}
fun main() { Main().start() }