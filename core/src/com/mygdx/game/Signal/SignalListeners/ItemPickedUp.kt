package com.mygdx.game.Signal.SignalListeners

import com.mygdx.game.Enums.ItemType
import com.mygdx.game.Signal.SIGNALTYPE
import com.mygdx.game.Signal.Signal
import com.mygdx.game.Signal.SignaledEventListener
import com.mygdx.game.itemObjectAddToInventory
import com.mygdx.game.player

class ItemPickedUp: SignaledEventListener {
    override val signaltype = SIGNALTYPE.ITEM_PICKED_UP

    override fun triggerEvent(signal: Signal) {
        val itemType = ItemType.values().first { it.ordinal == signal.id}
        player.inventory.addItem(itemType)
    }
}