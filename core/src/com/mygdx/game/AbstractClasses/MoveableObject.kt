package com.mygdx.game.AbstractClasses

import com.badlogic.gdx.math.Vector2
import com.mygdx.game.GameObjects.MoveableEntities.Characters.Player
import com.mygdx.game.Interfaces.MovementStrategy
import com.mygdx.game.Locations.DefaultLocation

abstract class MoveableObject(Position: Vector2, size: Vector2, defaultLocation: DefaultLocation?):GameObject(Position,size,defaultLocation){
    abstract var baseSpeed: Float
    abstract val movementStrategy: MovementStrategy
    private var canMove = true
    abstract var unitVectorDirection: Vector2
    var currentSpeed: Float? = null

    init {
        onLocationEnterActions.add(::resetObject)
    }

    open fun move(unitVectorDirection: Vector2): Boolean{
        if(canMove){
            val moveSuccessfull = movementStrategy.moveEntity(this,unitVectorDirection)
            return moveSuccessfull
        }else{
            return false
        }
    }

    fun freezeMoving(){
        canMove = false
    }
    fun enableMoving(){
        canMove = true
    }
    fun canMove():Boolean{
        return canMove
    }

    private fun resetObject(){
        if(this is Player){
            this.setPosition(Vector2(sprite.x,sprite.y))
        } else{
            this.setPosition(startingPosition)
        }
    }

    fun getCurrentSpeed(): Float{
        return currentSpeed ?: baseSpeed
    }

    fun setCurrentSpeed(float: Float) {
        currentSpeed = float
    }

}