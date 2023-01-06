package com.mygdx.game.GameObjects.Other

import com.badlogic.gdx.math.Polygon
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.Collitions.IllegalMoveCollition
import com.mygdx.game.DefaultTextureHandler
import com.mygdx.game.Enums.Layer
import com.mygdx.game.Locations.DefaultLocation
import com.mygdx.game.middleOfObject

class Tree(Position: Vector2, size: Vector2, defaultLocation: DefaultLocation) : GameObject(middleOfObject(Position, size), size,defaultLocation) {
    override val texture = DefaultTextureHandler.getTexture("World Tree.png")
    override val polygon = Polygon()
    override val collition = IllegalMoveCollition
    override val layer = Layer.AIR

    init {
        polygon.vertices = floatArrayOf(x + (21f * 2),  y + ((128 - 118).toFloat() * 2),
                 x + (23f * 2), y + ((128 - 92).toFloat() * 2),
               x + (21f * 2), y + ((128 - 66).toFloat() * 2),
                x + (42f * 2),  y + ((128 - 65).toFloat() * 2),
                x + (38f * 2), y + ((128 - 92).toFloat() * 2),
                x + (42f * 2), y + ((128 - 118.2).toFloat() * 2)
        )
    }
}