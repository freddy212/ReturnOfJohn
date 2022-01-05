package com.mygdx.game.Collitions

import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.AbstractClasses.CharacterAbility
import com.mygdx.game.GameObjects.ItemObjects.AbilityItemObject
import com.mygdx.game.GameObjects.MoveableEntities.Characters.Player
import com.mygdx.game.Interfaces.MoveCollition
import com.mygdx.game.Managers.SignalManager
import com.mygdx.game.Signal.SIGNALTYPE
import com.mygdx.game.Signal.Signal
import com.mygdx.game.player

class AbilityGainedCollition(val ability:CharacterAbility):MoveCollition by CanMoveCollition {

    override fun collitionHappened(entity: GameObject, collidedObject: GameObject) {
        if(entity is Player && collidedObject is AbilityItemObject){
            SignalManager.emitSignal(Signal( SIGNALTYPE.ABILITY_GAINED, collidedObject.ability.abilityId.ordinal))
            SignalManager.emitSignal(Signal( SIGNALTYPE.REMOVE_OBJECT, collidedObject.entityId))
        }
    }
}