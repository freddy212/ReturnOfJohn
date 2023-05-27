package com.mygdx.game.Signal.SignalListeners

import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.getAbility
import com.mygdx.game.GameObjects.ItemObjects.AbilityItemObject
import com.mygdx.game.Managers.LocationManager
import com.mygdx.game.Signal.Signal
import com.mygdx.game.Signal.SignaledEventListener
import com.mygdx.game.Signal.Signals.AddAbilityItemSignal
import com.mygdx.game.Signal.Signals.SIGNALTYPE

class AddAbilityItem: SignaledEventListener {
    override val signaltype = SIGNALTYPE.ADD_ABILITY_ITEM

    override fun triggerEvent(signal: Signal) {
        val addAbilityItemSignal = signal as AddAbilityItemSignal
        val characterAbility = getAbility(signal.abilityId)
        val location = LocationManager.findLocation(addAbilityItemSignal.locationName, addAbilityItemSignal.area)

        val abilityItemObject = AbilityItemObject(Vector2(addAbilityItemSignal.x, addAbilityItemSignal.y), Vector2(50f,50f), location, characterAbility)

        abilityItemObject.addToLocation(location)
    }
}