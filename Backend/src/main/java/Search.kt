
//class Search (app : Application){

  //  val searchType : SearchType? = null

class Search (val app : Application) {

    val perfectSearch: PerfectMatch = PerfectMatch()
    val parcialSearch: PartialMatch = PartialMatch()

    fun RestByCode(restCode: Int): Restaurant? {
        return perfectSearch.searchRestaurant(restCode.toString(), this.app.restaurants)
    }

    fun MenuByCode(id : Int) : MutableList<Menu>{
        return app.restaurants.flatMap { elem -> elem.menus.filter { elem -> elem.code == id } }.toMutableList()
    }

}
