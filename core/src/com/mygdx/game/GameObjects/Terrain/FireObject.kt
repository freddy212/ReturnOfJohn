package com.mygdx.game.GameObjects.Terrain

import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.Collitions.FireCollition
import com.mygdx.game.DefaultTextureHandler
import com.mygdx.game.Enums.Layer
import com.mygdx.game.Events.RemoveGameObjectEvent
import com.mygdx.game.LocationImpl
import com.mygdx.game.ObjectProperties.Fire

class FireObject(Position: Vector2, size: Vector2, objectAttached: GameObject, location:LocationImpl) : GameObject(Position, size,location) {
        override val collition = FireCollition(objectAttached.collition)
        override val texture = DefaultTextureHandler.getTexture("Sensor.png")
        override val layer = Layer.ONGROUND
        override val polygon = objectAttached.polygon
        override fun render(batch: PolygonSpriteBatch) {}

        init {
            properties.add(Fire(RemoveGameObjectEvent(this),this))
        }
}