
import exceptions.ExceptionSignup
import org.junit.Test

import kotlin.test.assertEquals

class ApplicationTest {

    val restaurants: MutableList<Restaurant> = mutableListOf()
    val users: MutableList<Customer> = mutableListOf()
    val pMethods: MutableList<PaymentMethods> = mutableListOf()
    val aplication = Application(restaurants, pMethods)


    @Test
    fun testICanCreateAUser(){
        assertEquals(0, aplication.customers.size)
        aplication.newCustomer("pepe","ad","123", "fake street",1.0,1.0)
        assertEquals(1, aplication.customers.size)
        assertEquals("pepe", aplication.customers[0].userName)
        assertEquals("123", aplication.customers[0].password)
    }

    @Test(expected = ExceptionSignup::class)
    fun testICantCreateAUserIfItAlreadyExists(){
        aplication.newCustomer("pepe","ad","123", "fake street",1.0,1.0)
        aplication.newCustomer("pepe","ad","123", "fake street",1.0,1.0)
    }
    
}