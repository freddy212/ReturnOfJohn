package com.mygdx.game.AbstractClasses

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.Enums.Layer

class GenericGameObject(Position: Vector2, size: Vector2,textureName: String,override val layer: Layer): GameObject(Position, size) {
    override val texture = Texture(textureName)
}