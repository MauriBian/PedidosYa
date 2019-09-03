

class Menu ( override val name : String, var description : String, var products : MutableList<Product>, var optionalDiscount: OptionalDiscount, var discountAmount : Double, var enabled : Boolean) : Searchable {
    val code = CodeGenerator.newCode()
    fun totalPrice():Double{
        var total : Double  = products.fold(0.0){acc : Double,product: Product -> acc + product.price * product.amount }
        when(optionalDiscount) {
            OptionalDiscount.PORCENTUAL ->  total = total - total * (discountAmount/100.0)
            OptionalDiscount.FIJO -> total = total - discountAmount
            OptionalDiscount.NINGUNO -> total

        }
        return total
    }

    fun products():List<Product>{
        return products
    }
}

enum class OptionalDiscount(){PORCENTUAL,FIJO,NINGUNO}