package com.mygdx.game.GameObjects.MoveableEntities

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.DefaultMovement
import com.mygdx.game.AbstractClasses.DefaultPositionChange
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.AbstractClasses.MoveableObject
import com.mygdx.game.Collitions.WaterGunCollition
import com.mygdx.game.EdgeOfLocationStrategies.NoAction
import com.mygdx.game.Enums.Direction
import com.mygdx.game.Enums.Layer
import com.mygdx.game.Interfaces.DynamicEntity
import com.mygdx.game.Interfaces.MovementStrategy
import com.mygdx.game.LocationImpl
import com.mygdx.game.handleCollitions
import com.mygdx.game.player

class WaterGunSpray(Position: Vector2, size: Vector2, location: LocationImpl?): GameObject(Position, size, location),DynamicEntity by DefaultPositionChange {
    override val texture = Texture("Sensor.png")
    override val layer = Layer.ONGROUND

    init {
        sprite.setOrigin(0f + size.x / 2,0f)
        polygon.setOrigin(0f + size.x / 2,0f)
    }
    override val collition = WaterGunCollition()


    fun changeDirection(direction: Direction){
        val rotation = when(direction){
            Direction.UP -> 0f
            Direction.LEFT -> 90f
            Direction.DOWN -> 180f
            Direction.RIGHT -> 270f
        }
        polygon.rotation = rotation
        sprite.rotation = rotation
    }

    override fun frameTask() {
        super.frameTask()
        setPosition(Vector2(player.sprite.x + ((player.size.x - size.x) / 2), player.sprite.y + player.size.y / 2),this)
        handleCollitions(this,polygon)
        changeDirection(player.direction)
    }
}