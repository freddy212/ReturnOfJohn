package com.mygdx.game.AbstractClasses

import com.badlogic.gdx.math.Vector2
import com.mygdx.game.Interfaces.DynamicEntity
import com.mygdx.game.Interfaces.MovementStrategy
import com.mygdx.game.Locations.DefaultLocation

abstract class MoveableObject(Position: Vector2, size: Vector2, defaultLocation: DefaultLocation?):GameObject(Position,size,defaultLocation),
        DynamicEntity by DefaultPositionChange{
    abstract var currentSpeed : Float
    abstract val movementStrategy: MovementStrategy
    private var canMove = true
    abstract val unitVectorDirection: Vector2

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
}