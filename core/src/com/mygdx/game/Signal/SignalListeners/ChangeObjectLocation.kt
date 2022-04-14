package com.mygdx.game.Signal.SignalListeners

import com.mygdx.game.Interfaces.AreaIdentifier
import com.mygdx.game.Managers.LocationManager
import com.mygdx.game.Signal.SIGNALTYPE
import com.mygdx.game.Signal.Signal
import com.mygdx.game.Signal.SignaledEventListener
import com.mygdx.game.getGameObjectWithEntityId

class ChangeObjectLocation: SignaledEventListener {
    override val signaltype = SIGNALTYPE.CHANGE_OBJECT_LOCATION

    override fun triggerEvent(signal: Signal) {
        val gameObject =  getGameObjectWithEntityId(signal.id)
        val identifier = AreaIdentifier.values().first { it.ordinal == signal.areaIdentifier}
        val otherLocation = LocationManager.findLocation(signal.stringValue,identifier)
        gameObject!!.removeFromLocation()
        gameObject!!.addToLocation(otherLocation)
    }
}