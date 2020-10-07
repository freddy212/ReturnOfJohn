package com.mygdx.game

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.math.Intersector.intersectSegments
import com.badlogic.gdx.math.Polygon
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.utils.FloatArray
import com.mygdx.game.AbstractClasses.*
import com.mygdx.game.Collitions.IllegalMoveCollition
import com.mygdx.game.GameObjects.*
import com.mygdx.game.Interfaces.Area


var font: BitmapFont = BitmapFont()


fun getPolygonPoints(polygon: Polygon): List<Vector2>{
        val floatArray = polygon.transformedVertices
        val xValues = floatArray.filterIndexed{index, x -> index.toFloat() % 2f == 0f}
        val yValues = floatArray.filterIndexed{index, y -> index % 2f == 1f}
        val listOfVectors = xValues.zip(yValues).map{ (xvalue,yvalue) -> Vector2(xvalue,yvalue) }
        return listOfVectors
}

fun getUnitVectorTowardsPoint(position: Vector2, point: Vector2): Vector2{
        return point.sub(position).nor()
}

enum class InsertDirection{LEFT,UP,RIGHT,DOWN,MIDDLE}
fun GetPositionRelativeToLocation(location: LocationImpl, size: Vector2, direction: InsertDirection, directionOnPlane:InsertDirection): Vector2{

        val DirectionOnPlane = when(direction){
                InsertDirection.LEFT, InsertDirection.RIGHT ->
                        when(directionOnPlane) {
                               InsertDirection.UP -> (location.topleft.y - location.bottomleft.y) - size.y
                               InsertDirection.DOWN -> 0f
                               else ->  ((location.topleft.y - location.bottomleft.y) / 2f - size.y / 2)
                        }
                InsertDirection.UP, InsertDirection.DOWN ->
                        when(directionOnPlane){
                                InsertDirection.RIGHT -> (location.topright.x - location.topleft.x) - size.x
                                InsertDirection.LEFT -> 0f
                                else -> (location.topright.x - location.topleft.x) / 2f - size.x / 2
                        }
                else -> 0f
        }

        return when(direction){
                InsertDirection.LEFT -> Vector2(location.bottomleft.x - size.x,
                location.bottomleft.y + DirectionOnPlane)
                InsertDirection.UP -> Vector2(location.topleft.x + DirectionOnPlane,
                        location.topleft.y)
                InsertDirection.RIGHT -> Vector2(location.bottomright.x,
                        location.bottomleft.y + DirectionOnPlane)
                InsertDirection.DOWN -> Vector2(location.topleft.x + DirectionOnPlane,
                        location.bottomleft.y - size.y)
                else -> Vector2(0f,0f)
        }
}

fun intersectPolygonEdges(polygon1: FloatArray, polygon2: FloatArray): Boolean {
        val last1 = polygon1.size - 2
        val last2 = polygon2.size - 2
        val p1 = polygon1.items
        val p2 = polygon2.items
        var x1 = p1[last1]
        var y1 = p1[last1 + 1]
        var i = 0
        while (i <= last1) {
                val x2 = p1[i]
                val y2 = p1[i + 1]
                var x3 = p2[last2]
                var y3 = p2[last2 + 1]
                var j = 0
                while (j <= last2) {
                        val x4 = p2[j]
                        val y4 = p2[j + 1]
                        if (intersectSegments(x1, y1, x2, y2, x3, y3, x4, y4, null)) return true
                        x3 = x4
                        y3 = y4
                        j += 2
                }
                x1 = x2
                y1 = y2
                i += 2
        }
        return false
}

fun addLocation(location: LocationImpl,area: Area): LocationImpl{
        area.addLocation(location)
        return location
}
fun addLocationRelative(location: LocationImpl, size:Vector2, direction:InsertDirection, area: Area,
                        directionOnPlane:InsertDirection,objectCreationMethod: () -> List<GameObject> = {listOf()},
                        texture: Texture = Texture("MainB.jpg")):LocationImpl{
        val pos1 = GetPositionRelativeToLocation(location,size,direction,directionOnPlane)
        val newLocation = LocationImpl(size,pos1, objectCreationMethod,texture)
        location.addAdjacentLocation(newLocation)
        newLocation.addAdjacentLocation(location)
        return addLocation(newLocation,area)
}

fun addGameObjectsToWorld(area: Area){
        area.locations.forEach{x -> x.initLocation()}
}

fun handleCollitions(intersectingObjects: List<GameObject>, moveableEntity: MoveableEntity, collitionPosition: Vector2) {
        val collitions = intersectingObjects.map { x -> x.collition }
        if(IllegalMoveCollition !in collitions){
                moveableEntity.sprite.setPosition(collitionPosition.x,collitionPosition.y)
                intersectingObjects.forEach { x -> x.collition.collitionHappened(moveableEntity, collitionPosition, x) }
        }
}

fun GameObject.InitSprite(texture: Texture): Sprite{
        val sprite = Sprite(texture)
        sprite.setSize(size.x,size.y)
        sprite.setOriginCenter()
        sprite.setPosition(Position.x,Position.y)
        /*if(this is Location){
                sprite.setPosition(Position.x,Position.y)
        }else{
                sprite.setPosition(Position.x - (size.x / 2), Position.y - (size.y / 2))
        }*/
        return sprite
}

inline fun ConstructObjects(gameobjectFactory: (Position: Vector2, Size: Vector2) -> GameObject , fromx: Int, incrementx: Int, endx: Int,
                     fromy: Int, incrementy: Int, endy: Int): List<GameObject>{
        val objects = mutableListOf<GameObject>()
        for(y in fromy downTo endy step incrementy){
                for(x in fromx..endx step incrementx){
                        val gameObject = gameobjectFactory(Vector2(x.toFloat(),y.toFloat()), Vector2(128f,128f))
                        objects.add(gameObject)
                }
        }

        //objects.map { x -> testRects.add(x.sprite.boundingRectangle) }

        return objects.toList()
}

fun constructTombs(location: LocationImpl): List<GameObject>{
        val tombFactory = {Position : Vector2, Size: Vector2 -> Tomb(Position,Size) }
        return ConstructObjects(tombFactory,location.bottomleft.x.toInt() + 20, 200, location.bottomright.x.toInt(),
                location.bottomleft.y.toInt() + 400, 201, location.bottomleft.y.toInt())
}

fun middleOfObject(Position: Vector2,size: Vector2): Vector2{
        return Vector2(Position.x - (size.x / 2), Position.y - (size.y / 2))
}

var counter = 0