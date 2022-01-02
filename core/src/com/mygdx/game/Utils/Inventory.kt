package com.mygdx.game

import com.mygdx.game.Enums.ItemType
import com.mygdx.game.SaveHandling.SaveableObject
import kotlinx.serialization.Serializable

@Serializable
data class ItemSavableObject(val itemType: ItemType, val amount: Int){}

@Serializable
class InventorySaveObject(val items: List<ItemSavableObject>, override val entityId: Int): SaveableObject(){}

class Inventory {
    private val InventoryList: MutableMap<ItemType,Int> = mutableMapOf()
    val inventoryList : Map<ItemType,Int>
        get() = InventoryList.toMap()
    fun addItem(itemType: ItemType, amount: Int = 1){
        InventoryList.putIfAbsent(itemType, 0)
        InventoryList[itemType] = getItemCount(itemType) + amount
    }
    fun getItemCount(itemType: ItemType): Int{
        return InventoryList.getOrDefault(itemType,0)
    }
    fun useItems(itemType: ItemType, amountToUse: Int): Boolean{
        val itemCount = getItemCount(itemType)
        if(amountToUse <= itemCount){
            InventoryList[itemType] = itemCount - amountToUse
            return true
        }
        return false
    }
    fun loadItems(listOfItems: List<ItemSavableObject>){
        listOfItems.forEach {this.addItem(it.itemType,it.amount)}
    }
    fun saveItems(){

    }
}