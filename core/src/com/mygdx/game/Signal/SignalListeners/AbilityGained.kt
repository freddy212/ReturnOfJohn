package com.mygdx.game.Signal.SignalListeners

import com.mygdx.game.AbstractClasses.AbilityId
import com.mygdx.game.AbstractClasses.CharacterAbility
import com.mygdx.game.AbstractClasses.getAbility
import com.mygdx.game.Enums.ItemType
import com.mygdx.game.Signal.SIGNALTYPE
import com.mygdx.game.Signal.Signal
import com.mygdx.game.Signal.SignaledEventListener
import com.mygdx.game.getGameObjectWithEntityId
import com.mygdx.game.player

class AbilityGained: SignaledEventListener {
    override val signaltype = SIGNALTYPE.ABILITY_GAINED

    override fun triggerEvent(signal: Signal) {
        val abilityType = AbilityId.values().first { it.ordinal == signal.id}
        val ability = getAbility(abilityType)
        player.addAbility(ability)
    }
}