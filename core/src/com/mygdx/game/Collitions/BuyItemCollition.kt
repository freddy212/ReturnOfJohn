package com.mygdx.game.Collitions

import com.badlogic.gdx.Input
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.GameObjects.Sensors.BuySensor
import com.mygdx.game.GameObjects.ShopItem.ShopItem
import com.mygdx.game.Interfaces.KeyPressedCollition
import com.mygdx.game.player

class BuyItemCollition(val shopItem: ShopItem): KeyPressedCollition {
    override val specificButton =  Input.Keys.B

    override fun collitionHappened(entity: GameObject, collidedObject: GameObject) {
        if(collidedObject is BuySensor){
            if(checkCanBuy()){
                Buy()
                player.addAbility(shopItem.ability)
                shopItem.removeFromLocation()
            }
        }
    }

    private fun checkCanBuy(): Boolean{
        for(item in shopItem.requiredItems){
            if(player.inventory.getItemCount(item.itemType) < item.amount){
                return false
            }
        }
        return true
    }

    private fun Buy(){
        for(item in shopItem.requiredItems){
            player.inventory.useItems(item.itemType,item.amount)
        }
    }
}