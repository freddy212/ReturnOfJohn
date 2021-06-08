package com.mygdx.game.Collitions

import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.GameObjects.MoveableEntities.Characters.Player
import com.mygdx.game.HitOppositeDirection
import com.mygdx.game.Interfaces.MoveCollition
import com.mygdx.game.player

class PlayerHitCollition: MoveCollition by IllegalMoveCollition {
    override fun collitionHappened(entity: GameObject, collidedObject: GameObject) {
        if(entity is Player){
            HitOppositeDirection(collidedObject, player)
        }
        else if(collidedObject is Player){
            HitOppositeDirection(entity, player)
        }
    }
}
