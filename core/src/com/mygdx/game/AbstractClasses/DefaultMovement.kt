package com.mygdx.game.AbstractClasses

import com.badlogic.gdx.math.Polygon
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.*
import com.mygdx.game.Interfaces.CannotMoveStrategy
import com.mygdx.game.Interfaces.MovementStrategy
import com.mygdx.game.Managers.LocationManager

class DefaultMovement(private val edgeOfLocationStrategy: CannotMoveStrategy): MovementStrategy{

    override fun moveEntity(moveableObject: MoveableObject,directionUnitVector:Vector2): Boolean{
        val sprite = moveableObject.sprite
        val speed = moveableObject.currentSpeed

        val nextIncrement = directionUnitVector * speed
        val polygonToCheck = Polygon(moveableObject.polygon.transformedVertices + nextIncrement)
        val canMove = handleCollitions(moveableObject,polygonToCheck,LocationManager.MoveCollitionGameObjects)
        val inLocation = entityWithinLocations(polygonToCheck)
        if(inLocation && canMove){
            moveableObject.setPosition(Vector2(sprite.x,sprite.y) + nextIncrement,moveableObject)
            return true
        }else{
            edgeOfLocationStrategy.CannotMoveAction(moveableObject)
            return false
        }
    }
}