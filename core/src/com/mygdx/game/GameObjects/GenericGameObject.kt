package com.mygdx.game.GameObjects

import com.mygdx.game.DefaultTextureHandler
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.Collitions.CanMoveCollition
import com.mygdx.game.Enums.Layer
import com.mygdx.game.Interfaces.Collition
import com.mygdx.game.LocationImpl

class GenericGameObject(Position: Vector2, size: Vector2,textureName: String,override val layer: Layer,location: LocationImpl, override val collition:Collition = CanMoveCollition): GameObject(Position, size,location) {
    override val texture = DefaultTextureHandler.getTexture(textureName)
}