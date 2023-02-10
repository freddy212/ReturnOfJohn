package com.mygdx.game.GameObjects.Hazards.ConveyerBelt

import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.Collitions.ConveyerBeltCollition
import com.mygdx.game.DefaultTextureHandler
import com.mygdx.game.Enums.Direction
import com.mygdx.game.Enums.Layer
import com.mygdx.game.Locations.DefaultLocation
import com.mygdx.game.minus
import com.mygdx.game.plus

class ConveyerBelt(initPosition: Vector2, size: Vector2, defaultLocation: DefaultLocation?, val direction: Direction) :
    GameObject(initPosition, size, defaultLocation) {
    override val texture = DefaultTextureHandler.getTexture("sensor.png")
    override val layer = Layer.PERSON
    private val isVertical = (direction == Direction.DOWN || direction == Direction.UP)
    private val brickLength = 20f
    private val brickCount = if(isVertical) size.y / brickLength else size.x / brickLength
    val offsetStartBrick = when(direction){
        Direction.DOWN -> Vector2(0f,0f)
        Direction.UP -> Vector2(0f,brickLength)
        Direction.RIGHT -> Vector2(0f,0f)
        Direction.LEFT -> Vector2(brickLength,0f)
    }
    val start = initPosition + if(isVertical) Vector2(0f,size.y) else Vector2(0f,0f)
    val end = if(isVertical) initPosition + offsetStartBrick else bottomright - Vector2(brickLength,0f)
    val brickImageFileName = if(isVertical) "ConveyerBrick.png" else "ConveyerBrickDown.png"
    val startBrick = ConveyerBrick(start + offsetStartBrick, if(isVertical) Vector2(size.x,brickLength) else Vector2(brickLength,size.y),1f,direction, DefaultTextureHandler.getTexture(brickImageFileName))
    val endBrick = ConveyerBrick(end, if(isVertical) Vector2(size.x,brickLength) else Vector2(brickLength,size.y),1f,direction, DefaultTextureHandler.getTexture(brickImageFileName))
    val bricks = constructBricks()
    override val collition = ConveyerBeltCollition(direction)
    override fun render(batch: PolygonSpriteBatch) {
        endBrick.draw(batch)
        startBrick.draw(batch)
        bricks.forEach { it.draw(batch) }
    }

    override fun frameTask() {
        bricks.forEach { it.move() }
        super.frameTask()
    }

    fun constructBricks(): List<ConveyerBrick>{
        val list = mutableListOf<ConveyerBrick>()
        for(i in 0 until brickCount.toInt()){
            val increment = if(isVertical) Vector2(0f, i * brickLength) else Vector2(-(i * brickLength), 0f)
            val brick = ConveyerBrick(start - increment, if(isVertical) Vector2(size.x,brickLength) else Vector2(brickLength,size.y),1f,direction, DefaultTextureHandler.getTexture(brickImageFileName))
            list.add(brick)
        }
        return list.toList()
    }
}