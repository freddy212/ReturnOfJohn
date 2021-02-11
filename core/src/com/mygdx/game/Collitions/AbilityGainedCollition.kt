package com.mygdx.game.Collitions

import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.AbstractClasses.CharacterAbility
import com.mygdx.game.GameObjects.ItemObjects.AbilityItemObject
import com.mygdx.game.GameObjects.MoveableEntities.Player
import com.mygdx.game.Interfaces.MoveCollition
import com.mygdx.game.SaveHandling.FileHandler
import com.mygdx.game.player

class AbilityGainedCollition(val ability:CharacterAbility):MoveCollition by CanMoveCollition {

    override fun collitionHappened(entity: GameObject, collidedObject: GameObject) {
        if(entity is Player && collidedObject is AbilityItemObject){
            player.addAbility(ability)
            collidedObject.removeFromLocation()
            FileHandler.writeSaveStateEntity(collidedObject)
        }
    }
}