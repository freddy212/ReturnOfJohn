package com.mygdx.game.GameObjects.MoveableEntities.Characters.Enemies.Bosses.SandGhost

import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.*
import com.mygdx.game.Collitions.PlayerHitCollition
import com.mygdx.game.DefaultTextureHandler
import com.mygdx.game.EdgeOfLocationStrategies.NoAction
import com.mygdx.game.Enums.Layer
import com.mygdx.game.Interfaces.FightableEntity
import com.mygdx.game.Interfaces.HealthStrategy
import com.mygdx.game.Interfaces.MoveCollition
import com.mygdx.game.Locations.DefaultLocation
import com.mygdx.game.SaveHandling.DefaultRemoveObjectSaveState
import com.mygdx.game.SaveState.SaveStateEntity
import kotlin.math.cos
import kotlin.math.sin
import com.mygdx.game.*
import com.mygdx.game.GameObjects.ItemAbilities.Shield
import com.mygdx.game.Trimer.DelayTimer

class SandHand(Position: Vector2, size: Vector2, location: DefaultLocation?, right: Boolean, val sandGhost: SandGhost)
    : MoveableObject(Position, size, location),
    SaveStateEntity by DefaultRemoveObjectSaveState(),
    FightableEntity{
    override val collition = SandHandCollition(this)
    override val texture = DefaultTextureHandler.getTexture("Hand.png")
    override val layer = Layer.PERSON
    override var currentSpeed = 1f
    override val movementStrategy = DefaultMovement(NoAction())
    override var unitVectorDirection = Vector2(0f,0f)
    val radius = 250f
    var angle = if(right) 0f else 180f
   // get() = if (angle < 0f) 359f else angle
    var increment = 1.5f

    init {
        if (right){
            val pos = Vector2(radius * cos(Radians(angle)), radius * sin(Radians(angle)))
            this.setPosition(this.currentPosition() + pos)
            this.sprite.flip(true,false)
            polygon.translate(polygon.x - 10f,polygon.y - 10f)
        } else {
            val pos = Vector2(radius * cos(Radians(angle)), radius * sin(Radians(angle)))
            this.setPosition(this.currentPosition() + pos)
            polygon.translate(polygon.x + 10f,polygon.y - 10f)
        }
        polygon.setScale(0.8f,0.8f)
    }

    override fun frameTask() {
        if(sandGhost.isAggroed()) {
            val currentPos = Vector2(radius * cos(Radians(angle)), radius * sin(Radians(angle)))
            this.setPosition(sandGhost.currentPosition() + currentPos)
            angle = (angle + increment) % 360f
            val newPos = Vector2(radius * cos(Radians(angle)), radius * sin(Radians(angle)))
            unitVectorDirection = (newPos - currentPos)
            super.move(unitVectorDirection)
        }

        super.frameTask()
    }

    override var health = 1000f
    override val maxHealth = 1000f
    override val healthStrategy = object : HealthStrategy{
        override fun showHealth(sprite: Sprite, health: Float, maxHealth: Float) {

        }

    }

    override fun HitAction(other: GameObject, thisEntity: FightableEntity) {
        this.isHit(Vector2(0f,0f))
    }

    override fun isHit(launchUnitVector: Vector2) {
        sandGhost.isHit(launchUnitVector)
    }
}

class SandHandCollition(val sandHand: SandHand): MoveCollition{
    val playerHitCollition = PlayerHitCollition()
    override val canMoveAfterCollition = true
    override fun collitionHappened(entity: GameObject, collidedObject: GameObject) {
        playerHitCollition.collitionHappened(entity,collidedObject)
        if(entity is SandHand && collidedObject is Shield){
            val collition = collidedObject.collition
            if(entity in collition.delayMap.keys){
                val delayTimer = collition.delayMap[entity]!!
                if(delayTimer.getTimeHasPassed()){
                    collition.delayMap.remove(entity)
                }
            } else {
                val delayTimer = DelayTimer(1f)
                collition.delayMap[entity] = delayTimer
                sandHand.increment = -sandHand.increment
            }
        }
    }
}