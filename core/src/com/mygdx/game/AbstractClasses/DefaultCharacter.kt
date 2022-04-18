package com.mygdx.game.AbstractClasses

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.math.Vector3
import com.mygdx.game.*
import com.mygdx.game.EdgeOfLocationStrategies.NoAction
import com.mygdx.game.Enums.CharacterState
import com.mygdx.game.Enums.getDirectionUnitVector
import com.mygdx.game.Interfaces.*
import com.mygdx.game.Locations.DefaultLocation
import com.mygdx.game.Managers.LocationManager

abstract class DefaultCharacter(Position: Vector2, size: Vector2, location: DefaultLocation?) :MoveableObject(Position, size, location),
                                RotationalObject by DefaultRotationalObject(),
                                DirectionalObject,
                                Character,
                                FightableEntity{
    override val font = BitmapFont()
    override val texture = DefaultTextureHandler.getTexture("man.png")
    val speedDecrease = 0.95f
    var characterState: CharacterState = CharacterState.FREE
        private set
    var originalSpeed: Float? = null
    var stunDuration = 4
    lateinit var launchUnitVector: Vector2
    override var canChangeDirection = true
    override var unitVectorDirection = Vector2(0f,0f)
    override val movementStrategy = DefaultMovement(NoAction())
    abstract fun death()

    init {
        font.data.setScale(2f)
        font.color = Color.WHITE
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
            if (characterState == CharacterState.FREE) {
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
        if(health <= 0f){
            death()
        }
    }
    override fun isHit(launchUnitVector: Vector2){
        this.health -= 10f
        originalSpeed = originalSpeed ?: currentSpeed
        characterState = CharacterState.STUNNED
        this.launchUnitVector = launchUnitVector
        currentSpeed = stunDuration * originalSpeed!!
        setCharacterRotation(launchUnitVector)
    }
    private fun handleStunned(directionUnitVector: Vector2){
        currentSpeed *= speedDecrease
        if(currentSpeed <= originalSpeed!!){
            currentSpeed = originalSpeed!!
            characterState = CharacterState.FREE
        }
        super.move(directionUnitVector)
    }
}