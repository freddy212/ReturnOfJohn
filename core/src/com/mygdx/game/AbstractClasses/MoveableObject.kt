package com.mygdx.game.AbstractClasses

import com.badlogic.gdx.math.Vector2
import com.mygdx.game.*
import com.mygdx.game.Enums.Direction
import com.mygdx.game.Interfaces.DynamicEntity
import com.mygdx.game.Interfaces.MovementStrategy

abstract class MoveableObject(Position: Vector2, size: Vector2, location: LocationImpl?):GameObject(Position,size,location),
        DynamicEntity by DefaultPositionChange{
    open fun move(d: Direction): Boolean{
        if(canAct){
            return movementStrategy.moveEntity(d,this)
        }else{
            return false
        }
    }
    abstract var speed : Float
    abstract var direction: Direction
    abstract val movementStrategy: MovementStrategy
    private var canAct = true

    fun freezeObject(){
        canAct = false
    }
    fun enableObject(){
        canAct = true
    }

}