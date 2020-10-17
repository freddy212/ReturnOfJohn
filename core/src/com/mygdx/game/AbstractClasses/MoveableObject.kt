package com.mygdx.game.AbstractClasses

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.utils.FloatArray
import com.mygdx.game.*
import com.mygdx.game.Enums.Direction
import com.mygdx.game.Interfaces.EdgeOfLocationStrategy
import com.mygdx.game.Interfaces.MoveableEntity
import com.mygdx.game.Interfaces.MovementStrategy
import com.mygdx.game.Managers.LocationManager

abstract class MoveableObject(Position: Vector2, size: Vector2, location: LocationImpl?):GameObject(Position,size,location),MoveableEntity {
    override fun move(d: Direction){
        movementStrategy.moveEntity(d,this)
    }
    open fun setPosition(position: Vector2){
        this.sprite.setPosition(position.x,position.y)
        polygon.setPosition(position.x - polygon.vertices[0],position.y - polygon.vertices[1])
    }
}