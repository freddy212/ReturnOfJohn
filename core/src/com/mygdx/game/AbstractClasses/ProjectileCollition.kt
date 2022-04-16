package com.mygdx.game.AbstractClasses

import com.mygdx.game.Interfaces.FightableEntity
import com.mygdx.game.Interfaces.MoveCollition

abstract class ProjectileCollition: MoveCollition{

    override fun collitionHappened(entity: GameObject, collidedObject: GameObject) {
        if(entity is Projectile && collidedObject is FightableEntity){
            collidedObject.HitAction(entity,collidedObject)
        }
    }

    override val canMoveAfterCollition = true
}