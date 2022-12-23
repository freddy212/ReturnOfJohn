package com.mygdx.game.AI.EnemyActions

import com.badlogic.gdx.math.Vector2
import com.mygdx.game.*
import com.mygdx.game.AbstractClasses.Enemy
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.AbstractClasses.Projectile
import com.mygdx.game.GameObjects.IceClone
import com.mygdx.game.GameObjects.MoveableEntities.Projectiles.Icicle
import com.mygdx.game.Interfaces.EnemyAction
import com.mygdx.game.Locations.DefaultLocation
import com.mygdx.game.Managers.LocationManager
import com.mygdx.game.Timer.DefaultTimer
import kotlin.random.Random
import kotlin.reflect.KFunction4

class ShootProjectile(val projectileFactory: (Position: Vector2, Size: Vector2, defaultLocation: DefaultLocation, unitVectorDirection:Vector2, shooter:GameObject) -> Projectile, val size:Vector2, val enemy:Enemy): EnemyAction() {
    val timer = DefaultTimer(2f)
    override fun executeEnemyAction() {
        generateEnemyProjectile(projectileFactory,enemy,size)
    }

    override val probability = 1.0

    override fun condition(): Boolean {
        return timer.tryUseCooldown()
    }

}