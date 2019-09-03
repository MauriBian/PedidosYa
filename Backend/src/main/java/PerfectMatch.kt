
class PerfectMatch () {

        fun searchRestaurant(text: String, restaurants : MutableList<Restaurant>)  : Restaurant?{
        return restaurants.firstOrNull{ x -> x.code == text.toInt()}
    }


}

