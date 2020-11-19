package com.mygdx.game.Collitions

import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.GameObjects.MoveableEntities.Boulder
import com.mygdx.game.GameObjects.MoveableEntities.Player
import com.mygdx.game.Interfaces.MoveCollition
import com.mygdx.game.Interfaces.DynamicEntity

class BoulderPlayerCollition: MoveCollition {
    override fun collitionHappened(entity: DynamicEntity, collidedObject: GameObject) {
        if(entity is Boulder){
            if(collidedObject is Player){
                println("collition happened")
                entity.location!!.removeGameObject(entity)
            }
        }
    }

    override var canMoveAfterCollition = true
}