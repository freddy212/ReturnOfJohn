package com.mygdx.game.Collitions

import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.GameObjects.MoveableEntities.Boulder
import com.mygdx.game.Interfaces.Collition
import com.mygdx.game.Interfaces.MoveableEntity

class BoulderGeneratorCollition: Collition by IllegalMoveCollition{
    override fun collitionHappened(entity: MoveableEntity, collitionPosition: Vector2, collidedObject: GameObject) {
        if(entity is Boulder) {
            entity.location!!.removeGameObject(entity)
        }
    }
}