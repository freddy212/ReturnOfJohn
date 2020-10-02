package com.mygdx.game.AbstractClasses

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.InitSprite

class GenericGameObject(Position: Vector2, size: Vector2,textureName: String): GameObject(Position, size) {
    val texture = Texture(textureName)
    override val sprite = InitSprite(texture)
}