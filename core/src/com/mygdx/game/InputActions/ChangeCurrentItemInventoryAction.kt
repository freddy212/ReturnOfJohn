package com.mygdx.game.InputActions

import com.badlogic.gdx.Input
import com.mygdx.game.Interfaces.Event
import com.mygdx.game.Interfaces.InputAction
import com.mygdx.game.Managers.EventManager
import com.mygdx.game.UI.Items.RenderInventory
import com.mygdx.game.Utils.RenderGraph
import com.mygdx.game.player

class ChangeCurrentItemInventoryAction(val renderInventory: RenderInventory): InputAction {
    override val keycodes = listOf(Input.Keys.RIGHT, Input.Keys.LEFT, Input.Keys.SPACE)

    override fun action(keycode: Int) {
       val amountOfItems = player.inventory.inventoryList.size
       if(keycode == Input.Keys.RIGHT || keycode == Input.Keys.SPACE){
           if(renderInventory.currentIndex + 1 == amountOfItems){
               renderInventory.currentIndex = 0
           }
           else {
               renderInventory.currentIndex = renderInventory.currentIndex + 1
           }
       }
       else if (keycode == Input.Keys.LEFT){
           if(renderInventory.currentIndex - 1 < 0){
               renderInventory.currentIndex = amountOfItems - 1
           }else{
               renderInventory.currentIndex = renderInventory.currentIndex - 1
           }
       }
    }
}