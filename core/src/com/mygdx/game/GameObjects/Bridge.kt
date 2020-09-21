package com.mygdx.game.GameObjects

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.GameObject

class Bridge(Position: Vector2,size: Vector2): GameObject(Position,size) {
    override val spritesToRender: List<Sprite>
        get() = listOf(Sprite(Texture("badlogic.jpg")))

}