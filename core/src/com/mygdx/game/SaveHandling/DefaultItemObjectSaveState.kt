package com.mygdx.game.SaveHandling

import com.mygdx.game.Enums.Item
import com.mygdx.game.SaveState.DefaultSaveStateHandler
import com.mygdx.game.SaveState.SaveStateEntity
import com.mygdx.game.player

class DefaultItemObjectSaveState(val item: Item, val amount:Int = 1): DefaultRemoveObjectSaveState() {
    override fun onLoadAction() {
        player.inventory.addItem(item,amount)
        super.onLoadAction()
    }
}