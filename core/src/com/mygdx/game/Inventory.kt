package com.mygdx.game

import com.mygdx.game.AbstractClasses.InventoryObject

class Inventory {
    private val inventoryList: MutableList<InventoryObject> = mutableListOf()
    val InventoryList : List<InventoryObject>
        get() = inventoryList.toList()
    fun addInventoryObject(inventoryObject: InventoryObject){
        inventoryList.add(inventoryObject)
    }
    fun getObject(objectName: String): InventoryObject{
        return InventoryList.find { x -> x.name == objectName }!!
    }
}