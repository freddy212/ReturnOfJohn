package com.mygdx.game.Signal.SignalListeners

import com.mygdx.game.Enums.ItemType
import com.mygdx.game.Signal.SIGNALTYPE
import com.mygdx.game.Signal.Signal
import com.mygdx.game.Signal.SignaledEventListener
import com.mygdx.game.player

class UseItems: SignaledEventListener {
    override val signaltype = SIGNALTYPE.USE_ITEMS

    override fun triggerEvent(signal: Signal) {
        val itemType = ItemType.values().first { it.ordinal == signal.id}
        player.inventory.useItems(itemType,signal.amount)
    }
}