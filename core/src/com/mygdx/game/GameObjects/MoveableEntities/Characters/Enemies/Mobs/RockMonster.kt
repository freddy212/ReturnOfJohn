package com.mygdx.game.GameObjects.MoveableEntities.Characters.Enemies.Mobs

import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AI.EnemyActions.EnemyMoveBasedOnPlayer
import com.mygdx.game.AI.EnemyActions.EnemyRoll
import com.mygdx.game.AI.EnemyActions.RandomAction
import com.mygdx.game.AI.EnemyActions.ShootProjectile
import com.mygdx.game.AbstractClasses.DefaultEnemyStrategy
import com.mygdx.game.AbstractClasses.Enemy
import com.mygdx.game.DefaultTextureHandler
import com.mygdx.game.Enums.Direction
import com.mygdx.game.Enums.Layer
import com.mygdx.game.GameObjects.MoveableEntities.Projectiles.SmallBoulder
import com.mygdx.game.Locations.DefaultLocation
import com.mygdx.game.Saving.DefaultSaveStateHandler
import com.mygdx.game.Saving.SaveStateEntity
import com.mygdx.game.Timer.DefaultTimer
import com.mygdx.game.getOppositeUnitVector
import com.mygdx.game.getUnitVectorTowardsPoint

class RockMonster(Position: Vector2, size: Vector2 = Vector2(128f,128f),location: DefaultLocation?)
    : Enemy(Position, size, location,100f),
    SaveStateEntity by DefaultSaveStateHandler() {
    override val texture = DefaultTextureHandler.getTexture("RockMan.png")
    override val layer = Layer.PERSON
    override var baseSpeed = 2f
    override var direction = Direction.UP
    private val randomAction = RandomAction(listOf(  EnemyMoveBasedOnPlayer(200f,::getOppositeUnitVector, this),
        EnemyMoveBasedOnPlayer(200f,::getUnitVectorTowardsPoint, this)),DefaultTimer(2f),this)
    override val enemyStrategy =  DefaultEnemyStrategy(listOf(randomAction,
        ShootProjectile(::SmallBoulder, Vector2(50f, 50f),this), EnemyRoll(EnemyMoveBasedOnPlayer(0f,::getUnitVectorTowardsPoint, this),this)))
    override var health = 50f
    override val maxHealth = 50f



}