package com.mygdx.game.AbstractClasses

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.*
import com.mygdx.game.EdgeOfLocationStrategies.NoAction
import com.mygdx.game.Enums.CharacterState
import com.mygdx.game.Enums.getDirectionUnitVector
import com.mygdx.game.Events.ImmuneEvent
import com.mygdx.game.Interfaces.*
import com.mygdx.game.Locations.DefaultLocation
import com.mygdx.game.Managers.EventManager
import com.mygdx.game.Managers.LocationManager

abstract class DefaultCharacter(Position: Vector2, size: Vector2, location: DefaultLocation?) :MoveableObject(Position, size, location),
                                RotationalObject by DefaultRotationalObject(),
                                DirectionalObject,
                                Character,
                                FightableEntity{
    override val font = BitmapFont()
    val speedDecrease = 0.95f
    var characterState: CharacterState = CharacterState.FREE
    open val stunDuration = 4
    lateinit var launchUnitVector: Vector2
    override var canChangeDirection = true
    override var unitVectorDirection = Vector2(0f,0f)
    override val movementStrategy = DefaultMovement(NoAction())
    val immunityFrames = 30f
    var immuneToDamage = false
    abstract fun death()

    init {
        font.data.setScale(2f)
        font.color = Color.WHITE
        onLocationEnterActions.add(::resetRotation)
    }

    fun setCharacterRotation(unitVectorDirection: Vector2) {
        this.setRotation(unitVectorDirection,this,90f)
        direction = getDirectionFromAngle(angle)
        this.unitVectorDirection = unitVectorDirection
    }
    override fun move(unitVectorDirection: Vector2): Boolean {
        this.unitVectorDirection = unitVectorDirection
        var moveSuccessfull = false
        if(canChangeDirection()){
            setCharacterRotation(unitVectorDirection)
        }
            if (characterState != CharacterState.STUNNED) {
                moveSuccessfull = super.move(unitVectorDirection)
                if (!moveSuccessfull) {
                    moveSuccessfull = super.move(getDirectionUnitVector(direction))
                }
            }
        return moveSuccessfull
    }

    override fun frameTask() {
        if(characterState == CharacterState.STUNNED){
            handleStunned(launchUnitVector)
        }
        handleCollitions(this, this.polygon, LocationManager.EveryFrameCollitionGameObjects)
        super.frameTask()
    }
    override fun isHit(other: GameObject){
        val launchUnitVector = getOppositeDirection(other)
        loseHealth(10f)
        characterState = CharacterState.STUNNED
        this.launchUnitVector = launchUnitVector
        currentSpeed = stunDuration * baseSpeed
        setCharacterRotation(launchUnitVector)
    }
    private fun handleStunned(directionUnitVector: Vector2){
        setCurrentSpeed(getCurrentSpeed() * speedDecrease)
        if(getCurrentSpeed() <= baseSpeed){
            setCurrentSpeed(baseSpeed)
            characterState = CharacterState.FREE
        }
        super.move(directionUnitVector)
    }
    fun loseHealth(amount: Float, immuneAfter: Boolean = true) {
        if(!immuneToDamage) {
            health -= amount
            if(health <= 0f){
                death()
            }
        }
        if(immuneAfter){
            makeImmune()
        }
    }

    fun makeImmune(immuneFrames:Float = immunityFrames){
        immuneToDamage = true
        EventManager.eventManager.add(ImmuneEvent(immuneFrames,this))
    }

    fun resetRotation(){
        setRotation(Vector2(0f,0f),this, 0f)
    }

    override fun render(batch: PolygonSpriteBatch) {
        if(immuneToDamage){
            sprite.setAlpha(0.5f)
        } else {
            sprite.setAlpha(1f)
        }
        super.render(batch)
    }
}