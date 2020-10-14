package com.mygdx.game.AbstractClasses

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.Enums.Layer
import com.mygdx.game.LocationImpl

class GenericGameObject(Position: Vector2, size: Vector2,textureName: String,override val layer: Layer,location: LocationImpl): GameObject(Position, size,location) {
    override val texture = Texture(textureName)
}