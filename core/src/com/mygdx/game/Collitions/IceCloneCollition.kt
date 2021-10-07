package com.mygdx.game.Collitions

import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.GameObjects.IceClone
import com.mygdx.game.GameObjects.MoveableEntities.Projectiles.Fireball
import com.mygdx.game.GameObjects.MoveableEntities.Projectiles.Icicle
import com.mygdx.game.Interfaces.MoveCollition

class IceCloneCollition: MoveCollition {
    override var canMoveAfterCollition = true

    override fun collitionHappened(entity: GameObject, collidedObject: GameObject) {
        canMoveAfterCollition = !(entity is Icicle && collidedObject is IceClone)

        if(entity is Fireball && collidedObject is IceClone){
            entity.defaultLocation!!.removeGameObject(entity)
            collidedObject.defaultLocation!!.removeGameObject(collidedObject)
        }
    }
}