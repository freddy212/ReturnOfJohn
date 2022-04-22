package com.mygdx.game.AbstractClasses

import com.badlogic.gdx.math.Vector2
import com.mygdx.game.Interfaces.DynamicEntity
import com.mygdx.game.Interfaces.MovementStrategy
import com.mygdx.game.Locations.DefaultLocation

abstract class MoveableObject(Position: Vector2, size: Vector2, defaultLocation: DefaultLocation?):GameObject(Position,size,defaultLocation){
    abstract var currentSpeed : Float
    abstract val movementStrategy: MovementStrategy
    private var canMove = true
    abstract var unitVectorDirection: Vector2

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
        this.setPosition(Position)
    }

}