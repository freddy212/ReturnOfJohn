package com.mygdx.game.Collitions

import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.GameObjects.MoveableEntities.Characters.Player
import com.mygdx.game.HitOppositeDirection
import com.mygdx.game.Interfaces.MoveCollition
import com.mygdx.game.player

class PlayerHitCollition: MoveCollition {
    override var canMoveAfterCollition = true

    override fun collitionHappened(entity: GameObject, collidedObject: GameObject) {

        if(entity is Player){
            player.HitAction(collidedObject, player)
        }

        else if(collidedObject is Player){
            player.HitAction(entity, player)
        }
    }
}
