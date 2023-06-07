package com.mygdx.game.GameObjects.Terrain

import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.Collitions.RemoveDotDamageCollition
import com.mygdx.game.DefaultTextureHandler
import com.mygdx.game.Enums.Layer
import com.mygdx.game.Locations.DefaultLocation

class WalkableTerrain(Position: Vector2, size: Vector2, defaultLocation: DefaultLocation, var textureName: String = "MainB.jpg") : GameObject(Position, size, defaultLocation) {
    override val texture = DefaultTextureHandler.getTexture(textureName)
    override val layer = Layer.GROUND
    override val collition = RemoveDotDamageCollition(defaultLocation.collition)
}