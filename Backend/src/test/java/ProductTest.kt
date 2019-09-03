import org.junit.Test
import java.util.*

import kotlin.test.assertEquals

class ProductTest {
    @Test fun testICanCreateAProduct(){
        val product = Product("sausages with mashed","very delicious",30.0, Category.PLATO_PRINCIPAL)
        //assertEquals(product.code,44)
        assertEquals(product.name,"sausages with mashed")
        assertEquals(product.description,"very delicious")
        assertEquals(product.price,30.0)
        assertEquals(product.category,Category.PLATO_PRINCIPAL)


    }
}