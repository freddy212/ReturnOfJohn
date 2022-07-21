package com.mygdx.game.AbstractClasses

import com.mygdx.game.Interfaces.FightableEntity
import com.mygdx.game.Interfaces.MoveCollition

abstract class ProjectileCollition(): MoveCollition{

    override fun collitionHappened(entity: GameObject, collidedObject: GameObject) {
        if(entity is Projectile && collidedObject is FightableEntity && entity.shooter != collidedObject){
            collidedObject.HitAction(entity,collidedObject)
            entity.removeFromLocation()
        }
        if(entity is FightableEntity && collidedObject is Projectile && collidedObject.shooter != entity){
            entity.HitAction(collidedObject,entity)
            collidedObject.removeFromLocation()
        }
    }

    override val canMoveAfterCollition = true
}