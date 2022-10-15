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

class ConveyerBelt(initPosition: Vector2, size: Vector2, defaultLocation: DefaultLocation?, val direction: Direction, val speed: Float) :
    GameObject(initPosition, size, defaultLocation) {
    override val texture = DefaultTextureHandler.getTexture("Sensor.png")
    override val layer = Layer.ONGROUND
    val brickheight = size.y / 8
    val start = initPosition + Vector2(0f,size.y)
    val endBrick = ConveyerBrick(initPosition, Vector2(100f,20f),speed,Direction.DOWN, DefaultTextureHandler.getTexture("ConveyerBrick.png"))
    val startBrick = ConveyerBrick(start, Vector2(100f,20f),speed,Direction.DOWN, DefaultTextureHandler.getTexture("ConveyerBrick.png"))
    val bricks = constructBricks()
    override val collition = ConveyerBeltCollition()
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
        for(i in 0 until 8){
            val increment = Vector2(0f, i * brickheight)
            val brick = ConveyerBrick(start - increment, Vector2(100f,20f),speed,Direction.DOWN, DefaultTextureHandler.getTexture("ConveyerBrick.png"))
            list.add(brick)
        }
        return list.toList()
    }
}