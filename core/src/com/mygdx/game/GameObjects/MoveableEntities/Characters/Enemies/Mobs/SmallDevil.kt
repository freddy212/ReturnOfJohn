package com.mygdx.game.GameObjects.MoveableEntities.Characters.Enemies.Mobs

import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AI.EnemyActions.MoveBasedOnPlayer
import com.mygdx.game.AI.EnemyActions.RandomAction
import com.mygdx.game.AI.EnemyActions.ShootProjectile
import com.mygdx.game.AbstractClasses.DefaultEnemyStrategy
import com.mygdx.game.AbstractClasses.Enemy
import com.mygdx.game.DefaultTextureHandler
import com.mygdx.game.Enums.Direction
import com.mygdx.game.Enums.Layer
import com.mygdx.game.GameObjects.MoveableEntities.Projectiles.Fireball
import com.mygdx.game.Locations.DefaultLocation
import com.mygdx.game.Saving.DefaultSaveStateHandler
import com.mygdx.game.Saving.SaveStateEntity
import com.mygdx.game.Timer.DefaultTimer
import com.mygdx.game.getOppositeUnitVector
import com.mygdx.game.getUnitVectorTowardsPoint

class SmallDevil(Position: Vector2, size: Vector2 = Vector2(128f,128f),location: DefaultLocation?)
    : Enemy(Position, size, location,100f),
    SaveStateEntity by DefaultSaveStateHandler() {
    override val texture = DefaultTextureHandler.getTexture("DefaultPerson.png")
    override val layer = Layer.PERSON
    override var baseSpeed = 2f
    override var direction = Direction.UP
    override var health = 100f
    override val maxHealth = 100f
    private val randomAction = RandomAction(listOf( MoveBasedOnPlayer(0f,::getUnitVectorTowardsPoint, this),
                                            MoveBasedOnPlayer(200f,::getUnitVectorTowardsPoint, this),
                                            MoveBasedOnPlayer(200f,::getOppositeUnitVector, this)),DefaultTimer(2f), this)
    override val enemyStrategy =  DefaultEnemyStrategy(listOf(randomAction,
                                                              ShootProjectile(::Fireball, Vector2(100f, 50f), this)))


}