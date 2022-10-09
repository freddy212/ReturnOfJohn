package com.mygdx.game.Signal.SignalListeners

import com.mygdx.game.NOTSAVEDID
import com.mygdx.game.Signal.Signal
import com.mygdx.game.Signal.SignaledEventListener
import com.mygdx.game.Signal.Signals.RemoveObjectSignal
import com.mygdx.game.Signal.Signals.SIGNALTYPE
import com.mygdx.game.getGameObjectWithEntityId

class RemoveObject: SignaledEventListener {
    override val signaltype = SIGNALTYPE.REMOVE_OBJECT
    override fun triggerEvent(signal: Signal) {
        val removeItemsSignal = signal as RemoveObjectSignal
        if(removeItemsSignal.entityId != NOTSAVEDID){
            val gameObject = getGameObjectWithEntityId(removeItemsSignal.entityId)
            gameObject?.removeFromLocation()
        }
    }
}