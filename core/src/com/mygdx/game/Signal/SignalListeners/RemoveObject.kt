package com.mygdx.game.Signal.SignalListeners

import com.mygdx.game.NOTSAVEDID
import com.mygdx.game.Signal.SIGNALTYPE
import com.mygdx.game.Signal.Signal
import com.mygdx.game.Signal.SignaledEventListener
import com.mygdx.game.getGameObjectWithEntityId

class RemoveObject: SignaledEventListener {
    override val signaltype = SIGNALTYPE.REMOVE_OBJECT
    override fun triggerEvent(signal: Signal) {
        if(signal.id != NOTSAVEDID){
            val gameObject = getGameObjectWithEntityId(signal.id)
            gameObject?.removeFromLocation()
        }
    }
}