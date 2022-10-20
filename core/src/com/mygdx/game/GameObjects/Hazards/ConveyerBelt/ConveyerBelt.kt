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
    override val texture = DefaultTextureHandler.getTexture("Sensor.png")
    override val layer = Layer.PERSON
    val brickCount = size.y / 20f
    val brickheight = 20f
    val start = initPosition + Vector2(0f,size.y)
    val offsetStartBrick = if(direction == Direction.DOWN) Vector2(0f,0f) else Vector2(0f,20f)
    val startBrick = ConveyerBrick(start + offsetStartBrick, Vector2(size.x,20f),1f,direction, DefaultTextureHandler.getTexture("ConveyerBrick.png"))
    val endBrick = ConveyerBrick(initPosition + offsetStartBrick, Vector2(size.x,20f),1f,direction, DefaultTextureHandler.getTexture("ConveyerBrick.png"))
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
            val increment = Vector2(0f, i * brickheight)
            val brick = ConveyerBrick(start - increment, Vector2(size.x,20f),1f,direction, DefaultTextureHandler.getTexture("ConveyerBrick.png"))
            list.add(brick)
        }
        return list.toList()
    }
}