package com.mygdx.game.AI.EnemyActions

import com.badlogic.gdx.math.Vector2
import com.mygdx.game.*
import com.mygdx.game.AbstractClasses.Enemy
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.AbstractClasses.Projectile
import com.mygdx.game.Interfaces.EnemyAction
import com.mygdx.game.Locations.DefaultLocation
import com.mygdx.game.Timer.DefaultTimer

class ShootProjectile(val projectileFactory: (Position: Vector2, Size: Vector2, defaultLocation: DefaultLocation, unitVectorDirection:Vector2, shooter:GameObject) -> Projectile, val size:Vector2, val enemy:Enemy, probability: Double = 0.1): EnemyAction() {
    val timer = DefaultTimer(2f)
    override fun executeEnemyAction() {
        generateEnemyProjectile(projectileFactory,enemy,size)
    }

    override val probability = probability

    override fun condition(): Boolean {
        return timer.tryUseCooldown()
    }

}