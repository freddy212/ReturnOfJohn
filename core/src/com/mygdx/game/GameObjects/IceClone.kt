package com.mygdx.game.GameObjects

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.Collitions.IceCloneCollition
import com.mygdx.game.DefaultTextureHandler
import com.mygdx.game.Enums.Layer
import com.mygdx.game.Locations.DefaultLocation

class IceClone(Position: Vector2, size: Vector2, defaultLocation: DefaultLocation?) :
    GameObject(Position, size, defaultLocation) {
    override val texture = DefaultTextureHandler.getTexture("IceClone.png")
    override val layer = Layer.ONGROUND
    override val collition = IceCloneCollition()

}