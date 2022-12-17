package com.mygdx.game.GameObjects.SensorObjects

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.DefaultTextureHandler
import com.mygdx.game.Enums.Layer
import com.mygdx.game.Locations.DefaultLocation

class SandGhostSleeping(initPosition: Vector2, size: Vector2, defaultLocation: DefaultLocation?) :
    GameObject(initPosition, size, defaultLocation) {
    override val texture = DefaultTextureHandler.getTexture("BossFace.png")
    override val layer = Layer.GROUND
}