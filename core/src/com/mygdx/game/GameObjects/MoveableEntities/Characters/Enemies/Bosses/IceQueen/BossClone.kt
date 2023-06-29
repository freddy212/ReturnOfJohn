package com.mygdx.game.GameObjects.MoveableEntities.Characters.Enemies.Bosses.IceQueen

import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AI.EnemyActions.MoveTowardsPoint
import com.mygdx.game.AI.EnemyActions.RandomAction
import com.mygdx.game.AbstractClasses.DefaultEnemyStrategy
import com.mygdx.game.AbstractClasses.Enemy
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.Enums.Direction
import com.mygdx.game.GameObjects.MoveableEntities.Characters.Enemies.Bosses.Boss
import com.mygdx.game.HealthStrategy.BossHealthStrategy
import com.mygdx.game.Interfaces.*
import com.mygdx.game.Locations.DefaultLocation
import com.mygdx.game.Timer.DefaultTimer
import com.mygdx.game.minus

class BossClone(val boss: Boss): Enemy(boss.currentPosition(), boss.size, boss.defaultLocation,0f) {

    override var baseSpeed = boss.baseSpeed
    override val texture = boss.texture
    override val layer = boss.layer
    override var direction = Direction.DOWN
    override var health = boss.health
    override val maxHealth = boss.maxHealth
    override val collitionMask = IceQueenCloneCollitionMask()
    override val healthStrategy: HealthStrategy by lazy { BossHealthStrategy(this) }
    override var enemyStrategy = DefaultEnemyStrategy(listOf())

    override fun isHit(other: GameObject) {
        death()
    }

    init {
        sprite.setAlpha(0.5f)
    }

    override fun render(batch: PolygonSpriteBatch) {
        sprite.draw(batch)
    }
    fun setBossCloneActions(actions: List<EnemyAction>){
        enemyStrategy = DefaultEnemyStrategy(actions)
    }
}

class IceQueenCloneCollitionMask(): CollitionMask{
    override val canCollideWith = {gameObject: GameObject->  gameObject !is Boss}
}