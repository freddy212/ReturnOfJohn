package com.mygdx.game.AbstractClasses
import com.mygdx.game.Interfaces.FightableEntity
import com.mygdx.game.Interfaces.MoveCollition

open class ProjectileCollition(val projectile: Projectile): MoveCollition{

    override fun collitionHappened(collidedObject: GameObject) {
        if(collidedObject is FightableEntity && projectile.shooter != collidedObject){
            collidedObject.isHit(projectile)
            projectile.removeFromLocation()
        }
    }

    override val canMoveAfterCollition = true
}