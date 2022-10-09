package com.mygdx.game.Collitions

import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.AbstractClasses.CharacterAbility
import com.mygdx.game.GameObjects.ItemObjects.AbilityItemObject
import com.mygdx.game.GameObjects.MoveableEntities.Characters.Player
import com.mygdx.game.Interfaces.MoveCollition
import com.mygdx.game.Managers.SignalManager
import com.mygdx.game.Signal.Signal
import com.mygdx.game.Signal.Signals.AbilityGainedSignal
import com.mygdx.game.Signal.Signals.RemoveObjectSignal
import com.mygdx.game.player

class AbilityGainedCollition(val ability:CharacterAbility):MoveCollition by CanMoveCollition {

    override fun collitionHappened(entity: GameObject, collidedObject: GameObject) {
        if(entity is Player && collidedObject is AbilityItemObject){
            SignalManager.emitSignal(AbilityGainedSignal(collidedObject.ability.abilityId))
            SignalManager.emitSignal(RemoveObjectSignal(collidedObject.entityId))
        }
    }
}