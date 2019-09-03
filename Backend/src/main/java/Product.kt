

class Product ( val name:String,val description:String, val price:Double, val category : Category ){
    val code = CodeGenerator.newCode()
    var amount = 1
}

enum class Category{ENTRADA,PLATO_PRINCIPAL,POSTRE,BEBIDA}