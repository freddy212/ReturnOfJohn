package com.mygdx.game

import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.math.Intersector
import com.badlogic.gdx.math.Polygon
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.utils.FloatArray

abstract class MoveableEntity() {
    abstract val sprite:Sprite
    abstract var speed : Float
    fun move(d:Direction){
        var insideTerrainPolygon = false
        val currentPos = Vector2(sprite.x, sprite.y)
        val nextPos: Vector2 =
        when(d){
            Direction.UP -> Vector2(currentPos.x, currentPos.y + speed)
            Direction.LEFT -> Vector2(currentPos.x - speed, currentPos.y)
            Direction.RIGHT -> Vector2(currentPos.x + speed, currentPos.y)
            Direction.DOWN -> Vector2(currentPos.x, currentPos.y - speed)
        }
        val nextPolygonPos = RectanglePolygon(Vector2(nextPos.x,nextPos.y),
                sprite.boundingRectangle.width,sprite.boundingRectangle.height)
        for(polygon in TerrainManager.listOfPolygons){
            val orgPolygon = Polygon(polygon.vertices)
            if(orgPolygon.contains(nextPolygonPos)){
                insideTerrainPolygon = true
            }
        }
        if(insideTerrainPolygon) {
            if (TerrainManager.collitionPolygons.filter { p -> intersectPolygonEdges(FloatArray(nextPolygonPos.vertices), FloatArray(p.vertices))}.isEmpty()) {
                sprite.setPosition(nextPos.x, nextPos.y)
            }else{
                //sprite.setPosition(currentPos.x - nextPos.x - currentPos.x,currentPos.y)
            }
        }
    }
    fun render(batch: PolygonSpriteBatch){
        sprite.draw(batch)
    }
    fun getEntityPoly(): Polygon {
        val rect = sprite.boundingRectangle
        return RectanglePolygon(Vector2(rect.x,rect.y),rect.width,rect.height)
    }
}