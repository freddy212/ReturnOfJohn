package com.mygdx.game.SaveHandling

import com.mygdx.game.Enums.ItemType
import com.mygdx.game.player

class DefaultItemObjectSaveState(val itemType: ItemType, val amount:Int = 1): DefaultRemoveObjectSaveState() {
    override fun onLoadAction() {
        player.inventory.addItem(itemType,amount)
        super.onLoadAction()
    }
}