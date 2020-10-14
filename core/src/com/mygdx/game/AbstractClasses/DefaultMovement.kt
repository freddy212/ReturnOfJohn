package com.mygdx.game.AbstractClasses

import com.badlogic.gdx.math.Polygon
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.utils.FloatArray
import com.mygdx.game.*
import com.mygdx.game.Enums.Direction
import com.mygdx.game.Interfaces.EdgeOfLocationStrategy
import com.mygdx.game.Interfaces.MovementStrategy
import com.mygdx.game.Managers.LocationManager

class DefaultMovement(private val edgeOfLocationStrategy: EdgeOfLocationStrategy): MovementStrategy{

    override fun moveEntity(d: Direction, moveableObject: MoveableObject){
        val polygon = moveableObject.polygon
        moveableObject.direction = d
        val sprite = moveableObject.sprite
        val speed = moveableObject.speed
        var inLocation = false
        val nextPos = when(d){
            Direction.UP -> Vector2(0f,  speed)
            Direction.LEFT -> Vector2(- speed, 0f)
            Direction.RIGHT -> Vector2(speed, 0f)
            Direction.DOWN -> Vector2(0f,-speed)
        }
        val collidingObjects: List<GameObject> = LocationManager.ActiveGameObjects.filter { p -> intersectPolygonEdges(FloatArray(polygon.transformedVertices + nextPos), FloatArray(p.polygon.transformedVertices))
                || polygon.anyPointInPolygon(p.polygon)}
        val canMove = handleCollitions(collidingObjects,moveableObject, Vector2(nextPos.x,nextPos.y))
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
            moveableObject.setPosition(Vector2(sprite.x,sprite.y) + nextPos)
        }else{
            edgeOfLocationStrategy.handleEdgeOfLocation(moveableObject)
        }
    }
}