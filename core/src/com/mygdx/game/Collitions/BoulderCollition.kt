package com.mygdx.game.Collitions

import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.AbstractClasses.ProjectileCollition
import com.mygdx.game.GameObjects.BoulderGenerator
import com.mygdx.game.GameObjects.MoveableEntities.Projectiles.Boulder


class BoulderCollition: ProjectileCollition() {
    override fun collitionHappened(entity: GameObject, collidedObject: GameObject) {
        super.collitionHappened(entity, collidedObject)
        if(entity is Boulder && (collidedObject is BoulderGenerator || collidedObject is Boulder)){
            collidedObject.removeFromLocation()
            entity.removeFromLocation()
        }
    }

    override var canMoveAfterCollition = true
}