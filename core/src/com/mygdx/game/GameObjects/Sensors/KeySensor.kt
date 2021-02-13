package com.mygdx.game.GameObjects.Sensors

import com.mygdx.game.DefaultTextureHandler
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.Collitions.CheckKeyCollition
import com.mygdx.game.Enums.Layer
import com.mygdx.game.GameObjects.LockedDoor
import com.mygdx.game.LocationImpl

class KeySensor(Position: Vector2, size: Vector2, location: LocationImpl, val lockedDoor: LockedDoor) : GameObject(Position, size,location){
    override val texture = DefaultTextureHandler.getTexture("sensor.png")
    override val layer = Layer.ONGROUND
    override fun render(batch: PolygonSpriteBatch){
        sprite.draw(batch)
    }
    init {
        polygon.setPosition(location.originalMiddle.x - polygon.vertices[0] - sprite.width / 2,sprite.y - polygon.vertices[1] - sprite.height)
    }
    override val collition = CheckKeyCollition(lockedDoor)
}