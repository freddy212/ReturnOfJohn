package com.mygdx.game.AbstractClasses

import com.badlogic.gdx.math.Vector2
import com.mygdx.game.*
import com.mygdx.game.Enums.Direction
import com.mygdx.game.Interfaces.DirectionalObject
import com.mygdx.game.Interfaces.DynamicEntity
import com.mygdx.game.Interfaces.MovementStrategy

abstract class MoveableObject(Position: Vector2, size: Vector2, location: LocationImpl?):GameObject(Position,size,location), DirectionalObject,
        DynamicEntity by DefaultPositionChange{
    open fun move(d: Direction): Boolean{
        if(canChangeDirection()){
            this.direction = d
        }
        if(canMove){
            return movementStrategy.moveEntity(this)
        }else{
            return false
        }
    }
    abstract var speed : Float
    abstract val movementStrategy: MovementStrategy
    private var canMove = true
    override var canChangeDirection = true

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