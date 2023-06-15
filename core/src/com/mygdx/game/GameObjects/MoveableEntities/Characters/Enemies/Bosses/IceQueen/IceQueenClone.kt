package com.mygdx.game.GameObjects.MoveableEntities.Characters.Enemies.Bosses.IceQueen

import EnemyCollition
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AI.EnemyActions.MoveTowardsPoint
import com.mygdx.game.AI.EnemyActions.RandomAction
import com.mygdx.game.AbstractClasses.DefaultEnemyStrategy
import com.mygdx.game.AbstractClasses.Enemy
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.Collitions.IllegalMoveCollition
import com.mygdx.game.DefaultTextureHandler
import com.mygdx.game.Enums.Direction
import com.mygdx.game.Enums.Layer
import com.mygdx.game.GameObjects.MoveableEntities.Characters.Enemies.Bosses.Boss
import com.mygdx.game.GameObjects.MoveableEntities.Characters.Player
import com.mygdx.game.GameObjects.Terrain.TeleportPad
import com.mygdx.game.HealthStrategy.BossHealthStrategy
import com.mygdx.game.Interfaces.CollitionMask
import com.mygdx.game.Interfaces.EnemyStrategy
import com.mygdx.game.Interfaces.HealthStrategy
import com.mygdx.game.Interfaces.MoveCollition
import com.mygdx.game.Locations.DefaultLocation
import com.mygdx.game.Timer.DefaultTimer
import com.mygdx.game.minus

class IceQueenClone(Position: Vector2, size: Vector2, location: DefaultLocation?, val iceQueen: IceQueen, val teleportPoints: List<TeleportPad>): Enemy(Position, size, location,0f) {

    val halfPadSize = Vector2(teleportPoints[0].size.x / 4, teleportPoints[0].size.y)
    val bottomLeftMove = MoveTowardsPoint( this, teleportPoints[0].bottomleft - halfPadSize)
    val bottomRightMove = MoveTowardsPoint( this, teleportPoints[1].bottomleft - halfPadSize)
    val topLeftMove = MoveTowardsPoint( this, teleportPoints[2].bottomleft - halfPadSize)
    val topRightMove = MoveTowardsPoint( this, teleportPoints[3].bottomleft - halfPadSize)
    val randomMoveAction = RandomAction(listOf(bottomLeftMove, bottomRightMove, topLeftMove, topRightMove),
        DefaultTimer(1.5f), this, false)
    override val enemyStrategy = DefaultEnemyStrategy(listOf(randomMoveAction))

    override var baseSpeed = iceQueen.baseSpeed
    override val texture = iceQueen.texture
    override val layer = iceQueen.layer
    override var direction = Direction.DOWN
    override var health = iceQueen.health
    override val maxHealth = iceQueen.maxHealth
    override val collitionMask = IceQueenCloneCollitionMask()
    override val healthStrategy: HealthStrategy by lazy { BossHealthStrategy(this) }

    init {
        setAggroed()
    }

    override fun isHit(other: GameObject) {
        death()
    }

    init {
        sprite.setAlpha(0.5f)
    }

    override fun render(batch: PolygonSpriteBatch) {
        sprite.draw(batch)
    }
}

class IceQueenCloneCollitionMask(): CollitionMask{
    override val canCollideWith = {gameObject: GameObject->  gameObject !is Boss}
}