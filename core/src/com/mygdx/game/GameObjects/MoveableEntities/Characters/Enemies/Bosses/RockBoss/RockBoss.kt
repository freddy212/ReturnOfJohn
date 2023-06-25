package com.mygdx.game.GameObjects.MoveableEntities.Characters.Enemies.Bosses.RockBoss

import EnemyCollition
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AI.EnemyActions.*
import com.mygdx.game.AbstractClasses.DefaultEnemyStrategy
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.DefaultTextureHandler
import com.mygdx.game.Enums.Direction
import com.mygdx.game.Enums.Element
import com.mygdx.game.Enums.Layer
import com.mygdx.game.Events.DefaultEvent
import com.mygdx.game.GameObjects.MoveableEntities.Characters.Enemies.Bosses.Boss
import com.mygdx.game.GameObjects.MoveableEntities.Characters.Player
import com.mygdx.game.GameObjects.MoveableEntities.Projectiles.Fireball
import com.mygdx.game.GameObjects.MoveableEntities.Projectiles.Icicle
import com.mygdx.game.GameObjects.MoveableEntities.Projectiles.SmallBoulder
import com.mygdx.game.Locations.DefaultLocation
import com.mygdx.game.ObjectProperties.Fire
import com.mygdx.game.ObjectProperties.Ice
import com.mygdx.game.Signal.Signal
import com.mygdx.game.Timer.DefaultTimer
import com.mygdx.game.getUnitVectorTowardsPoint

class RockBoss(Position: Vector2, size: Vector2, location: DefaultLocation?, element: Element, signal: Signal?) : Boss(Position, size, location, signal) {
    override val texture = DefaultTextureHandler.getTexture("RockMan.png")
    override var baseSpeed = 4f
    override val layer = Layer.ONGROUND
    override var direction = Direction.DOWN
    override var health = 50f
    override val maxHealth = 50f

    override val collition = RockBossCollition(this)

    val projectile = when(element){
        Element.FIRE -> ::Fireball
        Element.ICE -> ::Icicle
        Element.EARTH -> ::SmallBoulder
    }
    val projSize = when(element){
        Element.FIRE -> Vector2(100f,33f)
        Element.ICE -> Vector2(100f,33f)
        Element.EARTH -> Vector2(50f,50f)
    }

    val moveUp = MoveDirection(this, Vector2(0.5f,0.5f))
    val moveLeft = MoveDirection(this, Vector2(0.5f, -0.5f))
    val moveDown = MoveDirection(this, Vector2(-0.5f, 0.5f))
    val moveRight = MoveDirection(this, Vector2(-0.5f, -0.5f))

  // val roll =  Roll(MoveBasedOnPlayer(0f,::getUnitVectorTowardsPoint, this), this)
    val randomAction = RandomAction(listOf(moveDown, moveUp, moveLeft, moveRight), DefaultTimer(1f), this, false)
    override val enemyStrategy =  DefaultEnemyStrategy(listOf(ShootProjectile(projectile,projSize,this), randomAction
    ))

    init {
        val property = when(element){
            Element.FIRE -> Fire( this)
            Element.ICE -> Ice(this)
            Element.EARTH -> null
        }
        if(property != null){
            properties.add(property)
        }
    }

}

class RockBossCollition(val rockBoss: RockBoss): EnemyCollition(rockBoss){
    override fun collitionHappened(collidedObject: GameObject) {
        if(collidedObject is Player && rockBoss.isRolling){
            rockBoss.isRolling = false
        }
        super.collitionHappened(collidedObject)
    }
}