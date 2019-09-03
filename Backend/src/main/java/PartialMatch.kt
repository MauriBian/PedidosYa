class PartialMatch {


    fun searchRestaurant(text: String, restaurants: MutableList<Restaurant>): MutableList<Restaurant> {
        return restaurants.filter { rest -> rest.name.toLowerCase().contains(text.toLowerCase()) }.toMutableList()
    }

    fun searchMenuInAllRestaurants(text: String, restaurants: MutableList<Restaurant>): MutableList<Pair<Menu, Restaurant>> {

        val menusInRestaurants = restaurants.map{
            restaurant-> restaurant.menus.filter{
            menu -> menu.name.contains(text)
        }.map{
            menu -> Pair(menu, restaurant)
        }
        }.flatten().toMutableList()
        return menusInRestaurants
    }

    fun searchMenuInRestaurant(text: String, restaurant: Restaurant): MutableList<Menu> {

        return restaurant.menus.filter { menu -> menu.name.contains(text) }.toMutableList()
    }

    fun searchMenuAndRestaurant(text: String , restaurants: MutableList<Restaurant>): MutableList<Searchable>{

        val listRest : List<Restaurant> = restaurants.filter{ rest -> rest.name.contains(text)}
        val listMenu : List<Menu> = restaurants.map{ rest -> rest.menus.filter { menu -> menu.name.contains(text) }}.flatten()

            return (listRest + listMenu).toMutableList()
    }

    fun searchProductsByName(name : String , products : MutableList<Product>) : MutableList<Product>{
        return products.filter { elem -> elem.name.contains(name) }.toMutableList()
    }
}










