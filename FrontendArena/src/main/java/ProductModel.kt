import org.uqbar.commons.model.annotations.Observable

@Observable


class ProductModel (var code:Int, var name:String,var description:String, var price:Double, var category : Category ){
    var product : Product? = null
    var codeTemp : Int = 0
    var nameTemp : String = ""
    var descriptionTemp : String = ""
    var priceTemp : Double = 0.0
    var categoryTemp : Category = Category.POSTRE

    var categories = listOf<Category>(Category.POSTRE,Category.BEBIDA,Category.ENTRADA,Category.ENTRADA)

    constructor() : this(0,"","",0.0,Category.POSTRE)
     init {
         product = Product(name,description,price,category)
     }

     fun prodToProdModel( prod : Product) : ProductModel{
         return ProductModel(prod.code,prod.name,prod.description,prod.price,prod.category)
     }

    fun syncProduct(){
        product = Product(name,description,price, category)
    }

    fun productToProductModel(product : Product) : ProductModel{

        return ProductModel(product.code,product.name,product.description,product.price,product.category)
    }

    fun initModifProduct(){

        codeTemp = code
        nameTemp = name
        descriptionTemp = description
        priceTemp = price
        categoryTemp = category
    }


 }