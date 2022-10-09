package com.mygdx.game.Signal.SignalListeners

import com.mygdx.game.Interfaces.AreaIdentifier
import com.mygdx.game.Managers.LocationManager
import com.mygdx.game.Signal.Signal
import com.mygdx.game.Signal.SignaledEventListener
import com.mygdx.game.Signal.Signals.ChangeObjectLocationSignal
import com.mygdx.game.Signal.Signals.SIGNALTYPE
import com.mygdx.game.getGameObjectWithEntityId

class ChangeObjectLocation: SignaledEventListener {
    override val signaltype = SIGNALTYPE.CHANGE_OBJECT_LOCATION

    override fun triggerEvent(signal: Signal) {
        val changeObjectLocationSignal = signal as ChangeObjectLocationSignal
        val gameObject =  getGameObjectWithEntityId(changeObjectLocationSignal.entityId)
        val identifier = changeObjectLocationSignal.area
        val otherLocation = LocationManager.findLocation(changeObjectLocationSignal.location,identifier)
        gameObject!!.removeFromLocation()
        gameObject!!.addToLocation(otherLocation)
    }
}