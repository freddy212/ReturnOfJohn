package com.mygdx.game.AbstractClasses

import com.badlogic.gdx.math.Vector2
import com.mygdx.game.*
import com.mygdx.game.Enums.Direction
import com.mygdx.game.Interfaces.DirectionalObject
import com.mygdx.game.Interfaces.DynamicEntity
import com.mygdx.game.Interfaces.MovementStrategy

abstract class MoveableObject(Position: Vector2, size: Vector2, location: LocationImpl?):GameObject(Position,size,location),
        DynamicEntity by DefaultPositionChange{
    abstract var currentSpeed : Float
    abstract val movementStrategy: MovementStrategy
    private var canMove = true
    open var unitVectorDirection = Vector2(0f,0f)

    open fun move(unitVectorDirection: Vector2): Boolean{
        if(canMove){
            return movementStrategy.moveEntity(this,unitVectorDirection)
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