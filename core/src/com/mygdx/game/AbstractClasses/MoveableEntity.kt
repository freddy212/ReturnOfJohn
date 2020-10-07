package com.mygdx.game.AbstractClasses

import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.utils.FloatArray
import com.mygdx.game.*
import com.mygdx.game.Enums.Direction
import com.mygdx.game.Interfaces.Renderable
import com.mygdx.game.Managers.LocationManager

abstract class MoveableEntity: Renderable {
    abstract var speed : Float
    abstract var direction: Direction
    fun move(d: Direction){
        direction = d
        var canMove = false
        val currentPos = Vector2(sprite.x, sprite.y)
        val nextPos = when(d){
            Direction.UP -> Vector2(currentPos.x, currentPos.y + speed)
            Direction.LEFT -> Vector2(currentPos.x - speed, currentPos.y)
            Direction.RIGHT -> Vector2(currentPos.x + speed, currentPos.y)
            Direction.DOWN -> Vector2(currentPos.x, currentPos.y - speed)
        }
        val nextPolygonPos = RectanglePolygon(Vector2(nextPos.x, nextPos.y),
                sprite.boundingRectangle.width, sprite.boundingRectangle.height)
        for(point in getPolygonPoints(nextPolygonPos)){
            canMove = false
            for(rectangle in LocationManager.locations.map { x -> x.sprite.boundingRectangle }){
                if(rectangle.contains(point)){
                    canMove = true
                    break
                }
            }
            if(!canMove){
                break
            }
        }
        if(canMove) {
            val collidingObjects: List<GameObject> = LocationManager.ActiveGameObjects.filter { p -> intersectPolygonEdges(FloatArray(nextPolygonPos.vertices), FloatArray(p.polygon.vertices))}
            handleCollitions(collidingObjects,this, Vector2(nextPos.x,nextPos.y))
                /*if (intersectingPolygons.isEmpty()) {
                sprite.setPosition(nextPos.x, nextPos.y)
            }else{
                //sprite.setPosition(currentPos.x - nextPos.x - currentPos.x,currentPos.y)
            }*/
        }
    }
    fun frameAction(){
        RenderGraph.addToSceneGraph(this)
    }
    override fun render(batch: PolygonSpriteBatch){
        sprite.draw(batch)
    }
}