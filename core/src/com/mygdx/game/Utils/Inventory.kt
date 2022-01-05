package com.mygdx.game

import com.mygdx.game.Enums.Item
import com.mygdx.game.Enums.ItemType
import com.mygdx.game.Managers.SignalManager
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
    fun useItems(itemType: ItemType, amountToUse: Int){
        val itemCount = getItemCount(itemType)
        InventoryList[itemType] = itemCount - amountToUse
    }
    fun checkItemsEnough(item:Item): Boolean{
        return getItemCount(item.itemType) >= item.amount
    }
    fun loadItems(listOfItems: List<ItemSavableObject>){
        listOfItems.forEach {this.addItem(it.itemType,it.amount)}
    }
    fun saveItems(){

    }
}