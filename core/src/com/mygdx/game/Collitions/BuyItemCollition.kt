package com.mygdx.game.Collitions

import com.badlogic.gdx.Input
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.GameObjects.MoveableEntities.Characters.Player
import com.mygdx.game.GameObjects.Sensors.BuySensor
import com.mygdx.game.GameObjects.ShopItem.ShopItem
import com.mygdx.game.Interfaces.KeyPressedCollition
import com.mygdx.game.Managers.SignalManager
import com.mygdx.game.Signal.Signal
import com.mygdx.game.Signal.Signals.AbilityGainedSignal
import com.mygdx.game.Signal.Signals.RemoveObjectSignal
import com.mygdx.game.Signal.Signals.UseItemsSignal
import com.mygdx.game.UI.Items.RenderShopItem
import com.mygdx.game.Utils.RenderGraph
import com.mygdx.game.player

class BuyItemCollition(val shopItem: ShopItem): KeyPressedCollition() {
    override val specificButton =  Input.Keys.B

    override fun collitionHappened(collidedObject: GameObject) {
        if(collidedObject is Player){
            if(Buy(shopItem)){
                SignalManager.emitSignal(AbilityGainedSignal(shopItem.ability.abilityId))
                SignalManager.emitSignal(RemoveObjectSignal(shopItem.entityId))
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
            SignalManager.emitSignal(UseItemsSignal(it.itemType, it.amount))
        }
        return true
    }

    override fun renderKeyToUI(entity: GameObject, collidedObject: GameObject) {
        super.renderKeyToUI(entity, collidedObject)
        RenderGraph.addToSceneGraph(RenderShopItem(shopItem, player.inventory))
    }
}