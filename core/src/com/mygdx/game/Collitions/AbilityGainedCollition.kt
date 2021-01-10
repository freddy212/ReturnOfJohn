package com.mygdx.game.Collitions

import com.badlogic.gdx.Input
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.AbstractClasses.ItemAbility
import com.mygdx.game.GameObjects.MoveableEntities.Player
import com.mygdx.game.Interfaces.KeyPressedCollition
import com.mygdx.game.Interfaces.MoveCollition
import com.mygdx.game.player

class AbilityGainedCollition(val ability:ItemAbility):MoveCollition by CanMoveCollition {

    override fun collitionHappened(entity: GameObject, collidedObject: GameObject) {
        if(entity is Player){
            player.addItemAbility(ability)
            collidedObject.location!!.removeGameObject(collidedObject)
        }
    }
}