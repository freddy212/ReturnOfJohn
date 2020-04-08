import com.mygdx.tests.TestGame
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class FirstTest{
    @Test
    fun something(){
        var x = 2
        Assertions.assertEquals(0,x)
    }
    @Test
    fun hello(){
        println("hey")
    }
}