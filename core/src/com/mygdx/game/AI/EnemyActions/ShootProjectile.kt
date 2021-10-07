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

class ShootProjectile(val projectileFactory: (Position: Vector2, Size: Vector2, defaultLocation: DefaultLocation, unitVectorDirection:Vector2) -> Projectile, val size:Vector2): EnemyAction {
    val timer = DefaultTimer(2f)
    override fun executeEnemyAction(enemy: Enemy) {
        var unitVector = getUnitVectorTowardsPoint(Vector2(enemy.sprite.x, enemy.sprite.y), Vector2(player.sprite.x, player.sprite.y))
        val random = Random.nextInt(2)
        val clone = LocationManager.newDefaultLocation.gameObjects.find { it is IceClone }
        if(random == 1 && clone != null){
            unitVector = getUnitVectorTowardsPoint(Vector2(enemy.sprite.x, enemy.sprite.y), Vector2(clone.sprite.x, clone.sprite.y))
        }
        val enemyStart = enemy.currentMiddle
        enemy.defaultLocation!!.addGameObject(projectileFactory(enemyStart + (unitVector * 100f) - Vector2(size.x / 2,size.y / 2),size,enemy.defaultLocation!!,unitVector))
    }

    override val probability = 1.0

    override fun condition(enemy: Enemy): Boolean {
        return timer.tryUseCooldown()
    }

}