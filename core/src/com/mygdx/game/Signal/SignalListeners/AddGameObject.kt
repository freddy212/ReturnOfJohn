package com.mygdx.game.Signal.SignalListeners

import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.Interfaces.AreaIdentifier
import com.mygdx.game.Locations.DefaultLocation
import com.mygdx.game.Managers.LocationManager
import com.mygdx.game.Signal.SIGNALTYPE
import com.mygdx.game.Signal.Signal
import com.mygdx.game.Signal.SignaledEventListener

class AddGameObject(val objectCreationMethod: (DefaultLocation) -> List<GameObject>): SignaledEventListener {
    override val signaltype = SIGNALTYPE.ADD_OBJECT

    override fun triggerEvent(signal: Signal) {
        // I need to have an additional check to not add anything twice.
        val identifier = AreaIdentifier.values().first { it.ordinal == signal.areaIdentifier}
        val location = LocationManager.findLocation(signal.stringValue,identifier)

        val objects = objectCreationMethod(location)
        objects.forEach {location.addGameObject(it)}
    }
}