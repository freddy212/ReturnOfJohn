package com.mygdx.game.GameObjects.Terrain

import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.Collitions.FireCollition
import com.mygdx.game.DefaultTextureHandler
import com.mygdx.game.Enums.Layer
import com.mygdx.game.Events.RemoveGameObjectEvent
import com.mygdx.game.Interfaces.MoveCollition
import com.mygdx.game.Locations.DefaultLocation
import com.mygdx.game.ObjectProperties.Fire
import com.mygdx.game.Saving.DefaultSaveStateHandler
import com.mygdx.game.Saving.SaveStateEntity

class FireObject(Position: Vector2, size: Vector2, objectAttached: GameObject, defaultLocation: DefaultLocation, collitionOnFire: MoveCollition) : GameObject(Position, size,defaultLocation),
                                                                                                        SaveStateEntity by DefaultSaveStateHandler() {
        val fire = Fire(RemoveGameObjectEvent(this),this)
        override val collition = FireCollition(objectAttached.collition,collitionOnFire)
        override val texture = DefaultTextureHandler.getTexture("sensor.png")
        override val layer = Layer.ONGROUND
        override val polygon = objectAttached.polygon
        override fun render(batch: PolygonSpriteBatch) {}

        init {
            properties.add(fire)
        }
}