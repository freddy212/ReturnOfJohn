import com.mygdx.game.Enums.ItemType
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
        inventory.addItem(ItemType.WORLDLEAF)
        inventory.addItem(ItemType.WORLDLEAF)

        Assertions.assertEquals(1,inventory.inventoryList.size)
        Assertions.assertEquals(2,inventory.getItemCount(ItemType.WORLDLEAF))
        Assertions.assertEquals(true, inventory.useItems(ItemType.WORLDLEAF,2))
        Assertions.assertEquals(false, inventory.useItems(ItemType.WORLDLEAF,2))
        Assertions.assertEquals(0,inventory.getItemCount(ItemType.WORLDLEAF))
    }
}