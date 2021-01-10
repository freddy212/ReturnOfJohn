package com.mygdx.game

import com.mygdx.game.Enums.Item

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
}