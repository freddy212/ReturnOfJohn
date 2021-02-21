package com.mygdx.game.GameObjects

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.Collitions.DOTCollition
import com.mygdx.game.DefaultTextureHandler
import com.mygdx.game.Enums.Layer
import com.mygdx.game.Interfaces.MoveCollition
import com.mygdx.game.LocationImpl
import com.mygdx.game.ObjectProperties.Ice

class IceGround(Position: Vector2, size: Vector2, location: LocationImpl?) :
    GameObject(Position, size, location) {
    override val collition = DOTCollition
    override val texture = DefaultTextureHandler.getTexture("Sensor.png")
    override val layer = Layer.ONGROUND
    override fun render(batch: PolygonSpriteBatch) {}

    init {
        properties.add(Ice(this))
    }
}