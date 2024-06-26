package com.mygdx.game

import com.badlogic.gdx.graphics.Texture
import com.mygdx.game.Enums.Item
import com.mygdx.game.Enums.ItemType
import com.mygdx.game.Managers.SignalManager
import com.mygdx.game.SaveHandling.SaveableObject
import kotlinx.serialization.Serializable

fun getItemTexture(itemType: ItemType): Texture {
    return when(itemType){
        ItemType.FLINT -> DefaultTextureHandler.getTexture("Flint.png")
        ItemType.WOOD -> DefaultTextureHandler.getTexture("wood.png")
        ItemType.KEY -> DefaultTextureHandler.getTexture("Key.png")
        ItemType.WORLDLEAF -> DefaultTextureHandler.getTexture("WorldLeaf.png")
        ItemType.FLUTEOFAWAKENING -> DefaultTextureHandler.getTexture("FluteOfAwakening.png")
        ItemType.FIRELANDSKEY -> DefaultTextureHandler.getTexture("FirelandsKey.png")
        ItemType.ICELANDSKEY -> DefaultTextureHandler.getTexture("IcelandsKey.png")
        ItemType.WASTELANDSKEY -> DefaultTextureHandler.getTexture("WastelandKey.png")
        ItemType.VEST -> DefaultTextureHandler.getTexture("Vest.png")
    }
}

fun getItemDescription(itemType: ItemType): String {
    return when (itemType){
        ItemType.FLINT -> "Flint"
        ItemType.WOOD -> "Wood"
        ItemType.KEY -> "Key"
        ItemType.WORLDLEAF -> "Leaf"
        ItemType.FLUTEOFAWAKENING -> "Flute with an awakening tune"
        ItemType.ICELANDSKEY -> "The master-key belonging to the icelands"
        ItemType.WASTELANDSKEY -> "The master-key belonging to the wasteland"
        ItemType.FIRELANDSKEY-> "The master-key belonging to the firelands"
        ItemType.VEST-> "Reduce damage from hostile terrain"
    }
}

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
}