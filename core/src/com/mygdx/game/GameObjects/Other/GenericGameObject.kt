package com.mygdx.game.GameObjects.Other
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.Collitions.CanMoveCollition
import com.mygdx.game.DefaultTextureHandler
import com.mygdx.game.Enums.Layer
import com.mygdx.game.Interfaces.Collition
import com.mygdx.game.Locations.DefaultLocation

open class GenericGameObject(Position: Vector2, size: Vector2, textureName: String, override val layer: Layer, defaultLocation: DefaultLocation?, override val collition:Collition = CanMoveCollition): GameObject(Position, size,defaultLocation) {
    override val texture = DefaultTextureHandler.getTexture(textureName)
}