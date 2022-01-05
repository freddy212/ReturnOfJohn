package com.mygdx.game.Collitions

import com.badlogic.gdx.Input
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.GameObjects.Sensors.BuySensor
import com.mygdx.game.GameObjects.ShopItem.ShopItem
import com.mygdx.game.Interfaces.KeyPressedCollition
import com.mygdx.game.Managers.SignalManager
import com.mygdx.game.Signal.SIGNALTYPE
import com.mygdx.game.Signal.Signal
import com.mygdx.game.player

class BuyItemCollition(val shopItem: ShopItem): KeyPressedCollition {
    override val specificButton =  Input.Keys.B

    override fun collitionHappened(entity: GameObject, collidedObject: GameObject) {
        if(collidedObject is BuySensor){
            if(Buy(shopItem)){
                SignalManager.emitSignal(Signal( SIGNALTYPE.ABILITY_GAINED, shopItem.ability.abilityId.ordinal))
                SignalManager.emitSignal(Signal( SIGNALTYPE.REMOVE_OBJECT, shopItem.entityId))
            }
                //shopItem.removeFromLocation()
            }
        }

    private fun Buy(shopItem: ShopItem): Boolean{
        for(item in shopItem.requiredItems){
            if(!player.inventory.checkItemsEnough(item)){
                return false
            }
        }
        shopItem.requiredItems.forEach {
            SignalManager.emitSignal(Signal(SIGNALTYPE.USE_ITEMS, it.itemType.ordinal, it.amount))
        }
        return true
    }
}