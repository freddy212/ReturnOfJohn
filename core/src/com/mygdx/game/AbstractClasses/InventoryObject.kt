package com.mygdx.game.AbstractClasses

abstract class InventoryObject(val name: String) {

    private var amount = 0

    fun addToInventory(){
        amount++
    }
    fun removeFromInventory(){
        amount--
    }
    fun getAmountInInventory(): Int {
        return amount
    }
}