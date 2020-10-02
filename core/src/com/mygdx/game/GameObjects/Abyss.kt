package com.mygdx.game.GameObjects

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.Collitions.AbyssCollition
import com.mygdx.game.InitSprite

class Abyss(Position: Vector2, size: Vector2): GameObject(Position, size){
        val abyssTexture = Texture("MainB.jpg")

        override val sprite = InitSprite(abyssTexture)
        init{
        sprite.color = Color.BLACK
        }
        override val collition = AbyssCollition
}