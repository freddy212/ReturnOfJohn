package com.mygdx.game.Collitions

import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.AbstractClasses.Projectile
import com.mygdx.game.AbstractClasses.ProjectileCollition

class DefaultProjectileCollition(projectile: Projectile): ProjectileCollition(projectile){
    override val canMoveAfterCollition = true
}
