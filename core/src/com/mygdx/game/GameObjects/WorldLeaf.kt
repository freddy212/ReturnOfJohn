package com.mygdx.game.GameObjects

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.Collitions.ItemCollition
import com.mygdx.game.InitSprite

class WorldLeaf(Position: Vector2, size: Vector2) : GameObject(Position, size) {
    val texture = Texture("WorldLeaf.png")
    override val sprite = InitSprite(texture)

    override val collition = ItemCollition
}