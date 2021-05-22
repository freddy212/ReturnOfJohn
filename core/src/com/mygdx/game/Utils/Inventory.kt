package com.mygdx.game

import com.mygdx.game.Enums.Item
import com.mygdx.game.SaveHandling.DefaultSaveableObject
import com.mygdx.game.SaveHandling.SaveableObject
import kotlinx.serialization.Serializable

@Serializable
data class ItemSavableObject(val item: Item, val amount: Int){}

@Serializable
class InventorySaveObject(val items: List<ItemSavableObject>, override val entityId: Int): SaveableObject(){}

class Inventory {
    private val InventoryList: MutableMap<Item,Int> = mutableMapOf()
    val inventoryList : Map<Item,Int>
        get() = InventoryList.toMap()
    fun addItem(item: Item, amount: Int = 1){
        InventoryList.putIfAbsent(item, 0)
        InventoryList[item] = getItemCount(item) + amount
    }
    fun getItemCount(item: Item): Int{
        return InventoryList.getOrDefault(item,0)
    }
    fun useItems(item: Item, amountToUse: Int): Boolean{
        val itemCount = getItemCount(item)
        if(amountToUse <= itemCount){
            InventoryList[item] = amountToUse - itemCount
            return true
        }
        return false
    }
    fun loadItems(listOfItems: List<ItemSavableObject>){
        listOfItems.forEach {this.addItem(it.item,it.amount)}
    }
    fun saveItems(){

    }
}