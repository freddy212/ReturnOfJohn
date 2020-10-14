package com.mygdx.game.GameObjects

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.AbstractClasses.GenericGameObject
import com.mygdx.game.Collitions.IllegalMoveCollition
import com.mygdx.game.Enums.Layer
import com.mygdx.game.LocationImpl

class Tomb(Position: Vector2, size: Vector2,location: LocationImpl) : GameObject(Position, size,location) {
    override val texture = Texture("Tomb.png")
    private val graveyardGrass = GenericGameObject(Vector2(Position.x, Position.y - 96f), Vector2(128f, 128f), "grass.png", Layer.ONGROUND,location)
    override val collition = IllegalMoveCollition
    override val layer = Layer.ONGROUND

    init {
        location.addGameObject(graveyardGrass)
    }
}