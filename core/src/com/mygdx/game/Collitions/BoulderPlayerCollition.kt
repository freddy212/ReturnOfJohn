package com.mygdx.game.Collitions

import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.GameObjects.MoveableEntities.Boulder
import com.mygdx.game.GameObjects.MoveableEntities.Player
import com.mygdx.game.Interfaces.Collition
import com.mygdx.game.Interfaces.MoveableEntity
import com.mygdx.game.Managers.MovableObjectManager

class BoulderPlayerCollition: Collition {
    override fun collitionHappened(entity: MoveableEntity, collitionPosition: Vector2, collidedObject: GameObject) {
        if(entity is Boulder){
            if(collidedObject is Player){
                /*collidedObject.sprite.setPosition(50f,50f)
                entity.location.removeGameObject(entity)*/
                println("collition happened")
                MovableObjectManager.removeMoveableObject(entity)
            }
        }
    }

    override var canMoveAfterCollition = true
}