package com.mygdx.game.GameObjects

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.Collitions.IllegalMoveCollition
import com.mygdx.game.DefaultTextureHandler
import com.mygdx.game.Enums.Layer
import com.mygdx.game.Interfaces.Collition
import com.mygdx.game.LocationImpl

class Thorns(Position: Vector2, size: Vector2, location: LocationImpl?) : GameObject(Position, size, location) {
    override val texture = DefaultTextureHandler.getTexture("Thorns.png")
    override val layer = Layer.ONGROUND
    override val collition = IllegalMoveCollition
}