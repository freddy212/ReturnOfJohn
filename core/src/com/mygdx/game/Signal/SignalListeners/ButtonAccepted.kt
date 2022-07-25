package com.mygdx.game.Signal.SignalListeners

import com.mygdx.game.GameObjects.DoorButton
import com.mygdx.game.Signal.SIGNALTYPE
import com.mygdx.game.Signal.Signal
import com.mygdx.game.Signal.SignaledEventListener
import com.mygdx.game.getGameObjectWithEntityId

class ButtonAccepted: SignaledEventListener {
    override val signaltype = SIGNALTYPE.BUTTON_ACCEPTED

    override fun triggerEvent(signal: Signal) {
        val button = getGameObjectWithEntityId(signal.id) as DoorButton
        button.activated = true
    }

}