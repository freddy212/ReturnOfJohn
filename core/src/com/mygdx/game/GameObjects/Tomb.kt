package com.mygdx.game.GameObjects

import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.Collitions.CanMoveCollition
import com.mygdx.game.DefaultTextureHandler
import com.mygdx.game.Enums.Layer
import com.mygdx.game.Locations.DefaultLocation

class Tomb(Position: Vector2, size: Vector2, defaultLocation: DefaultLocation) : GameObject(Position, size,defaultLocation) {
    override val texture = DefaultTextureHandler.getTexture("Tomb.png")
    private val graveyardGrass = GenericGameObject(Vector2(Position.x, Position.y - 96f), Vector2(128f, 128f), "grass.png", Layer.ONGROUND, defaultLocation)
    override val collition = CanMoveCollition
    override val layer = Layer.ONGROUND

    init {
        //graveyardGrass.properties.add(Fire(graveyardGrass.Position,graveyardGrass.size,DefaultEvent(),graveyardGrass))
        defaultLocation.addGameObject(graveyardGrass)
    }
}