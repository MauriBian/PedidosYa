import org.junit.Test
import kotlin.test.assertEquals

class MenuTest {
    @Test
    fun testTotalPriceWithoutDiscount(){
        val product = Product("sausages","Meat", 30.0, Category.PLATO_PRINCIPAL)
        val product2 = Product("mashed potatoes","vegetable",15.0, Category.PLATO_PRINCIPAL)
        product.amount = 2
        product2.amount = 2
        var products: MutableList<Product> = mutableListOf(product,product2)
        val menu = Menu("sausages whit mashed","good",products,OptionalDiscount.FIJO,0.0,true)
        val price = menu.totalPrice()
        assertEquals(price, 90.0)
    }

    @Test
    fun testTotalPriceWithDiscountFixed(){
        val product = Product("sausages","Meat",30.0, Category.PLATO_PRINCIPAL)
        val product2 = Product("mashed potatoes","vegetable",15.0, Category.PLATO_PRINCIPAL)
        product.amount = 2
        product2.amount = 2
        var products: MutableList<Product> = mutableListOf(product,product2)
        val menu = Menu("sausages whit mashed","good",products,OptionalDiscount.FIJO,30.0,true)
        val price = menu.totalPrice()
        assertEquals(price, 60.0)
    }

    @Test
    fun testTotalPriceWithDiscountPercentage(){
        val product = Product("sausages","Meat",30.0, Category.PLATO_PRINCIPAL)
        val product2 = Product("mashed potatoes","vegetable",15.0, Category.PLATO_PRINCIPAL)
        product.amount = 2
        product2.amount = 2
        var products: MutableList<Product> = mutableListOf(product,product2)
        val menu = Menu("sausages whit mashed","good",products,OptionalDiscount.PORCENTUAL,30.0,true)
        val price = menu.totalPrice()
        assertEquals(price, 63.0)
    }
}
