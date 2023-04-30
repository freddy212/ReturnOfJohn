package com.mygdx.game.GameObjects.MoveableEntities.Characters.Enemies.Bosses.IceQueen

import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AI.EnemyActions.EnemyMoveBasedOnPlayer
import com.mygdx.game.AI.EnemyActions.EnemyMoveTowardsPoint
import com.mygdx.game.AI.EnemyActions.RandomAction
import com.mygdx.game.AI.EnemyActions.ShootProjectile
import com.mygdx.game.AbstractClasses.DefaultEnemyStrategy
import com.mygdx.game.DefaultTextureHandler
import com.mygdx.game.Enums.Direction
import com.mygdx.game.Enums.Layer
import com.mygdx.game.GameObjects.MoveableEntities.Characters.Enemies.Bosses.Boss
import com.mygdx.game.GameObjects.MoveableEntities.Projectiles.Icicle
import com.mygdx.game.Locations.DefaultLocation
import com.mygdx.game.Timer.DefaultTimer
import com.mygdx.game.getUnitVectorTowardsPoint
import com.mygdx.game.minus

class IceQueen(Position: Vector2, size: Vector2, location: DefaultLocation?) : Boss(Position, size, location) {
    val topLeftMove = EnemyMoveTowardsPoint( this, (location?.currentMiddle!! - Vector2(350f, -200f)))
    val topRightMove = EnemyMoveTowardsPoint( this, (location?.currentMiddle!! - Vector2(- 250f, -200f)))

    val bottomLeftMove = EnemyMoveTowardsPoint( this, (location?.currentMiddle!! - Vector2(350f, 200f)))
    val bottomRightMove = EnemyMoveTowardsPoint( this, (location?.currentMiddle!! - Vector2(- 250f, 200f)))
    private val randomAction = RandomAction(listOf(bottomLeftMove, bottomRightMove, topLeftMove, topRightMove),
        DefaultTimer(2.5f), this, false)
    override val enemyStrategy = DefaultEnemyStrategy(listOf(randomAction,  ShootProjectile(::Icicle, Vector2(100f, 34f),this)))
    override var baseSpeed = 5f
    override val texture = DefaultTextureHandler.getTexture("IceQueen.png")
    override val layer = Layer.PERSON
    override var direction = Direction.DOWN
    override var health = 100f
    override val maxHealth = 100f
}