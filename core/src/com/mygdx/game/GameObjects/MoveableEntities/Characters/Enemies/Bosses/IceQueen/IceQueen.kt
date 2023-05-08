package com.mygdx.game.GameObjects.MoveableEntities.Characters.Enemies.Bosses.IceQueen

import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AI.EnemyActions.MoveTowardsPoint
import com.mygdx.game.AI.EnemyActions.RandomAction
import com.mygdx.game.AI.EnemyActions.ShootProjectile
import com.mygdx.game.AI.EnemyActions.TeleportToPoint
import com.mygdx.game.AbstractClasses.DefaultEnemyStrategy
import com.mygdx.game.DefaultTextureHandler
import com.mygdx.game.Enums.Direction
import com.mygdx.game.Enums.Layer
import com.mygdx.game.GameObjects.MoveableEntities.Characters.Enemies.Bosses.Boss
import com.mygdx.game.GameObjects.MoveableEntities.Projectiles.Icicle
import com.mygdx.game.GameObjects.Terrain.TeleportPad
import com.mygdx.game.Locations.DefaultLocation
import com.mygdx.game.Timer.DefaultTimer
import com.mygdx.game.minus

class IceQueen(Position: Vector2, size: Vector2, location: DefaultLocation?, teleportPoints: List<TeleportPad>) : Boss(Position, size, location) {
    val halfPadSize = Vector2(teleportPoints[0].size.x / 4, teleportPoints[0].size.y)
    val bottomLeftMove = MoveTowardsPoint( this, teleportPoints[0].bottomleft - halfPadSize)
    val bottomRightMove = MoveTowardsPoint( this, teleportPoints[1].bottomleft - halfPadSize)
    val topLeftMove = MoveTowardsPoint( this, teleportPoints[2].bottomleft - halfPadSize)
    val topRightMove = MoveTowardsPoint( this, teleportPoints[3].bottomleft - halfPadSize)
    val randomMoveAction = RandomAction(listOf(bottomLeftMove, bottomRightMove, topLeftMove, topRightMove),
        DefaultTimer(1.5f), this, false)
    /*private val randomTeleportAction = RandomAction(listOf(bottomLeftTeleport, bottomRightTeleport, topLeftTeleport, topRightTeleport),
        DefaultTimer(2.5f), this, canRepeat = false, changeAfterExecute = true
    )*/
    val iceQueenCloneAction = IceQueenCloneAction(this, listOf(teleportPoints[0], teleportPoints[1], teleportPoints[2], teleportPoints[3]))
    override val enemyStrategy = DefaultEnemyStrategy(listOf(randomMoveAction,  ShootProjectile(::Icicle, Vector2(100f, 34f),this),iceQueenCloneAction))
    override var baseSpeed = 3.5f
    override val texture = DefaultTextureHandler.getTexture("IceQueen.png")
    override val layer = Layer.PERSON
    override var direction = Direction.DOWN
    override var health = 100f
    override val maxHealth = 100f
}