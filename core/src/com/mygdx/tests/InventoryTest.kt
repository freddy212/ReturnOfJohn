import com.mygdx.game.Enums.Item
import com.mygdx.game.Inventory
import com.mygdx.tests.TestGame
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class InventoryTest : TestGame(){
    private lateinit var inventory: Inventory

    @BeforeEach
    fun init2(){
        inventory = Inventory()
    }
    @Test
    fun TestAddingAndRemoving(){
        inventory.addItem(Item.WORLDLEAF)
        inventory.addItem(Item.WORLDLEAF)

        Assertions.assertEquals(1,inventory.inventoryList.size)
        Assertions.assertEquals(2,inventory.getItemCount(Item.WORLDLEAF))
        Assertions.assertEquals(true, inventory.useItems(Item.WORLDLEAF,2))
        Assertions.assertEquals(false, inventory.useItems(Item.WORLDLEAF,2))
        Assertions.assertEquals(0,inventory.getItemCount(Item.WORLDLEAF))
    }
}