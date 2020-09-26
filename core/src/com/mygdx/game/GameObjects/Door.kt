package com.mygdx.game.GameObjects

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.InitSprite

class Door(Position: Vector2, size: Vector2): GameObject(Position,size) {
    override val spriteToRender = InitSprite(Texture("Door.png"))
}