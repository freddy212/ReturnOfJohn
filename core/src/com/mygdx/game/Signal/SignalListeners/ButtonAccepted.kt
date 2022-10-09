package com.mygdx.game.Signal.SignalListeners

import com.mygdx.game.GameObjects.DoorButton
import com.mygdx.game.Signal.Signal
import com.mygdx.game.Signal.SignaledEventListener
import com.mygdx.game.Signal.Signals.ButtonAcceptedSignal
import com.mygdx.game.Signal.Signals.SIGNALTYPE
import com.mygdx.game.getGameObjectWithEntityId

class ButtonAccepted: SignaledEventListener {
    override val signaltype = SIGNALTYPE.BUTTON_ACCEPTED

    override fun triggerEvent(signal: Signal) {
        val buttonAcceptedSignal = signal as ButtonAcceptedSignal
        val button = getGameObjectWithEntityId(buttonAcceptedSignal.entityId) as DoorButton
        button.activated = true
    }

}