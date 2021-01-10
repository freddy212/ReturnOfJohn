package com.mygdx.game.Collitions

import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.GameObjects.BoulderGenerator
import com.mygdx.game.GameObjects.MoveableEntities.Boulder
import com.mygdx.game.GameObjects.MoveableEntities.Player
import com.mygdx.game.Interfaces.MoveCollition
import com.mygdx.game.Interfaces.DynamicEntity

class BoulderCollition: MoveCollition {
    override fun collitionHappened(entity: GameObject, collidedObject: GameObject) {
        if(entity is Boulder && collidedObject is Player){
            entity.location!!.removeGameObject(entity)
            collidedObject.die()
        }
        if(entity is Boulder && collidedObject is BoulderGenerator){
            collidedObject.location!!.removeGameObject(collidedObject)
        }
    }

    override var canMoveAfterCollition = true
}