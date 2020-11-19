package com.mygdx.game.AbstractClasses

import com.badlogic.gdx.math.Polygon
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.*
import com.mygdx.game.Enums.Direction
import com.mygdx.game.Interfaces.EdgeOfLocationStrategy
import com.mygdx.game.Interfaces.MovementStrategy
import com.mygdx.game.Managers.LocationManager

class DefaultMovement(private val edgeOfLocationStrategy: EdgeOfLocationStrategy): MovementStrategy{

    override fun moveEntity(d: Direction, moveableObject: MoveableObject): Boolean{
        val polygon = moveableObject.polygon
        moveableObject.direction = d
        val sprite = moveableObject.sprite
        val speed = moveableObject.speed
        var inLocation = false
        val nextPos = GetNextStep(d,speed)
        val collidingObjects = GetCollidingObjects(LocationManager.MoveCollitionGameObjects - moveableObject,Polygon(polygon.transformedVertices + nextPos))
        val canMove = handleCollitions(collidingObjects,moveableObject)
        for(point in getPolygonPoints(Polygon(polygon.transformedVertices + nextPos))){
            inLocation = false
            for(rectangle in LocationManager.ActiveLocations.map { x -> x.sprite.boundingRectangle }){
                if(rectangle.contains(point)){
                    inLocation = true
                    break
                }
            }
            if(!inLocation){
                break
            }
        }
        if(inLocation && canMove){
            moveableObject.setPosition(Vector2(sprite.x,sprite.y) + nextPos,moveableObject)
            return true
        }else{
            edgeOfLocationStrategy.handleEdgeOfLocation(moveableObject)
            return false
        }
    }
}