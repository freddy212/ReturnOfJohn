package com.mygdx.game.GameObjects.MoveableEntities.Characters.Enemies.Bosses.IceQueen

import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AI.EnemyActions.MoveTowardsPoint
import com.mygdx.game.AI.EnemyActions.RandomAction
import com.mygdx.game.AI.EnemyActions.ShootProjectile
import com.mygdx.game.AbstractClasses.DefaultEnemyStrategy
import com.mygdx.game.DefaultTextureHandler
import com.mygdx.game.Enums.Direction
import com.mygdx.game.Enums.Layer
import com.mygdx.game.GameObjects.MoveableEntities.Characters.Enemies.Bosses.Boss
import com.mygdx.game.GameObjects.MoveableEntities.Characters.Enemies.Bosses.boss.BossCloneAction
import com.mygdx.game.GameObjects.MoveableEntities.Projectiles.Icicle
import com.mygdx.game.GameObjects.Terrain.TeleportPad
import com.mygdx.game.Locations.DefaultLocation
import com.mygdx.game.MusicLoader
import com.mygdx.game.Signal.Signal
import com.mygdx.game.Timer.DefaultTimer
import com.mygdx.game.minus

class IceQueen(Position: Vector2, size: Vector2, location: DefaultLocation?, teleportPoints: List<TeleportPad>, signal: Signal?) : Boss(Position, size, location, signal) {

    override var baseSpeed = 3.5f
    override val texture = DefaultTextureHandler.getTexture("IceQueen.png")
    override val layer = Layer.PERSON
    override var direction = Direction.DOWN
    override var health = 100f
    override val maxHealth = 100f

    val halfPadSize = Vector2(teleportPoints[0].size.x / 4, teleportPoints[0].size.y)

    val bottomLeftMove = MoveTowardsPoint( this, teleportPoints[0].bottomleft - halfPadSize)
    val bottomRightMove = MoveTowardsPoint( this, teleportPoints[1].bottomleft - halfPadSize)
    val topLeftMove = MoveTowardsPoint( this, teleportPoints[2].bottomleft - halfPadSize)
    val topRightMove = MoveTowardsPoint( this, teleportPoints[3].bottomleft - halfPadSize)
    val randomMoveAction = RandomAction(listOf(bottomLeftMove, bottomRightMove, topLeftMove, topRightMove),
        DefaultTimer(1.5f), this, false)


    var bossClone = BossClone(this)
    val bottomLeftMoveClone = MoveTowardsPoint( bossClone, teleportPoints[0].bottomleft - halfPadSize)
    val bottomRightMoveClone = MoveTowardsPoint( bossClone, teleportPoints[1].bottomleft - halfPadSize)
    val topLeftMoveClone = MoveTowardsPoint( bossClone, teleportPoints[2].bottomleft - halfPadSize)
    val topRightMoveClone = MoveTowardsPoint( bossClone, teleportPoints[3].bottomleft - halfPadSize)
    val randomMoveActionClone = RandomAction(listOf(bottomLeftMoveClone, bottomRightMoveClone, topLeftMoveClone, topRightMoveClone),
        DefaultTimer(1.5f), bossClone, false)

    val iceQueenCloneAction = BossCloneAction(this, bossClone, listOf(randomMoveActionClone))
    override val enemyStrategy = DefaultEnemyStrategy(listOf(randomMoveAction,  ShootProjectile(::Icicle, Vector2(100f, 34f),this),iceQueenCloneAction))

    init {
        this.attachedMusic = MusicLoader.iceQueenMusic
    }
}