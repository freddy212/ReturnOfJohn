package com.mygdx.game.GameObjects

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.Collitions.AbyssCollition
import com.mygdx.game.Enums.Layer

class Abyss(Position: Vector2, size: Vector2): GameObject(Position, size){
        override val texture = Texture("MainB.jpg")
        override val collition = AbyssCollition
        override val layer = Layer.ONGROUND
        val fine = polygon.boundingRectangle
        init{
        sprite.color = Color.BLACK
        }
}