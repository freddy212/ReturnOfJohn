package com.mygdx.game.Signal.SignalListeners

import com.mygdx.game.Enums.ItemType
import com.mygdx.game.Signal.Signal
import com.mygdx.game.Signal.SignaledEventListener
import com.mygdx.game.Signal.Signals.SIGNALTYPE
import com.mygdx.game.Signal.Signals.UseItemsSignal
import com.mygdx.game.player

class UseItems: SignaledEventListener {
    override val signaltype = SIGNALTYPE.USE_ITEMS

    override fun triggerEvent(signal: Signal) {
        val useItemsSignal = signal as UseItemsSignal
        player.inventory.useItems(useItemsSignal.itemType,signal.amount)
    }
}