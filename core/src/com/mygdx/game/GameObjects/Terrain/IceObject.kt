package com.mygdx.game.GameObjects.Terrain

import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.Collitions.CanMoveCollition
import com.mygdx.game.Collitions.DOTCollition
import com.mygdx.game.DefaultTextureHandler
import com.mygdx.game.Enums.Layer
import com.mygdx.game.GameObjects.Other.GenericGameObject
import com.mygdx.game.Locations.DefaultLocation
import com.mygdx.game.ObjectProperties.Ice

class IceObject(Position: Vector2, size: Vector2, defaultLocation: DefaultLocation?) :
    GameObject(Position, size, defaultLocation) {
    override val collition = DOTCollition()
    override val texture = DefaultTextureHandler.getTexture("sensor.png")
    override val layer = Layer.ONGROUND
    override fun render(batch: PolygonSpriteBatch) {}

    init {
        properties.add(Ice(this))
    }
}