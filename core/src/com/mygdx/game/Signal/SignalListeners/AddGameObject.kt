package com.mygdx.game.Signal.SignalListeners

import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.Interfaces.AreaIdentifier
import com.mygdx.game.Locations.DefaultLocation
import com.mygdx.game.Managers.LocationManager
import com.mygdx.game.Signal.Signal
import com.mygdx.game.Signal.SignaledEventListener
import com.mygdx.game.Signal.Signals.AddObjectSignal
import com.mygdx.game.Signal.Signals.SIGNALTYPE

enum class ADDMETHODS{ENGINEER,DOOR1,DOOR2, FROSTFIRETOFIRELANDSDOOR, FIRELANDSTOFROSTFIREDOOR}

class AddGameObject(val objectCreationMethod: (DefaultLocation) -> List<GameObject>, val addMethod: ADDMETHODS): SignaledEventListener {
    override val signaltype = SIGNALTYPE.ADD_OBJECT

    override fun triggerEvent(signal: Signal) {
        val addObjectSignal = signal as AddObjectSignal
        // I need to have an additional check to not add anything twice.
        if(addMethod == addObjectSignal.addMethod) {
            val identifier = addObjectSignal.areaIdentifier
            val location = LocationManager.findLocation(addObjectSignal.location, identifier)

            val objects = objectCreationMethod(location)
            objects.forEach { location.addGameObject(it) }
        }
    }
}