package com.mygdx.game.GameObjects

import com.badlogic.gdx.graphics.Color
import com.mygdx.game.DefaultTextureHandler
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.Collitions.AbyssCollition
import com.mygdx.game.Enums.Layer
import com.mygdx.game.LocationImpl

class Abyss(Position: Vector2, size: Vector2, location: LocationImpl): GameObject(Position, size,location){
        override val texture = DefaultTextureHandler.getTexture("MainB.jpg")
        override val collition = AbyssCollition
        override val layer = Layer.ONGROUND
        val fine = polygon.boundingRectangle
        init{
        sprite.color = Color.BLACK
        }
}