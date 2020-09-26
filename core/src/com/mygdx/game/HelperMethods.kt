package com.mygdx.game

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.math.Intersector.intersectSegments
import com.badlogic.gdx.math.Polygon
import com.badlogic.gdx.math.Vector
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.utils.FloatArray
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.AbstractClasses.MoveableEntity
import com.mygdx.game.Collitions.NoCollition
import com.mygdx.game.GameObjects.Abyss
import com.mygdx.game.GameObjects.Bridge
import com.mygdx.game.GameObjects.Fence
import com.mygdx.game.GameObjects.House
import com.mygdx.game.Interfaces.Collition


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
fun GetPositionRelativeToLocation(location: Location, size: Vector2, direction: InsertDirection, directionOnPlane:InsertDirection): Vector2{

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

fun addTerrains(texture: Texture){
        val location1 = addTerrain(texture,Vector2(1024f,1024f),Vector2(0f,0f))
        location1.addGameObject(House(location1.middle.x,location1.middle.y, 150f, 200f))
        val horizontalHallway = Vector2(1500f,500f)
        val verticalHallway = Vector2(300f,800f)
        val location2 = addTerrainRelative(location1,horizontalHallway,InsertDirection.LEFT,InsertDirection.UP,texture)
        val location3 = addTerrainRelative(location1,verticalHallway,InsertDirection.UP,InsertDirection.MIDDLE,texture)
        val location4 = addTerrainRelative(location1,horizontalHallway,InsertDirection.RIGHT,InsertDirection.MIDDLE,texture)
        val bridge = Bridge(Vector2(location4.middle.x,location4.middle.y - 100f), Vector2(500f, 200f))
        location4.addGameObject(bridge)
        location4.addGameObject(Abyss(Vector2(location4.middle.x,location4.bottomleft.y), Vector2(bridge.bottomright.x - bridge.bottomleft.x
                ,bridge.bottomleft.y - location4.bottomleft.y)))
        val location5 = addTerrainRelative(location1,verticalHallway,InsertDirection.DOWN,InsertDirection.MIDDLE,texture)
        val locationSprite2 = addTerrainRelative(location2,verticalHallway,InsertDirection.UP,InsertDirection.MIDDLE,texture)

        val graveyardLoc = addTerrainRelative(location4,Vector2(1000f,2000f),InsertDirection.RIGHT,InsertDirection.MIDDLE,texture)
        val fence = Fence(Vector2(location4.bottomright.x,location4.bottomright.y - 100f), Vector2(graveyardLoc.middle.x - location4.bottomright.x,100f))
        graveyardLoc.addGameObject(fence)

        val leftDown = addTerrainRelative(location1, Vector2(horizontalHallway.x / 2,horizontalHallway.y / 2),
                                          InsertDirection.LEFT,InsertDirection.DOWN,texture)
}

fun addTerrain(texture: Texture, size: Vector2, position: Vector2 ): Location{
        val location = Location(texture,size,position)
        TerrainManager.addTerrain(location)
        return location
}
fun addTerrainRelative(location: Location,size:Vector2,direction:InsertDirection,directionOnPlane:InsertDirection,texture: Texture):Location{
        val pos1 = GetPositionRelativeToLocation(location,size,direction,directionOnPlane)
        return addTerrain(texture,size,pos1)
}

fun handleCollitions(intersectingPolygons: List<Collition>, moveableEntity: MoveableEntity, collitionPosition: Vector2) {
        var nocollition = NoCollition()
        if(intersectingPolygons.isEmpty()){
                NoCollition().collitionHappened(moveableEntity,collitionPosition)
        }
        else{
                intersectingPolygons.forEach{x -> x.collitionHappened(moveableEntity,collitionPosition)}
        }
}

fun GameObject.InitSprite(Texture: Texture): Sprite{
        val sprite = Sprite(Texture)
        sprite.setSize(size.x,size.y)
        sprite.setOriginCenter()
        sprite.setPosition(Position.x,Position.y)
        return sprite
}