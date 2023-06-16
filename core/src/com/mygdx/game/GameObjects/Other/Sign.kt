package com.mygdx.game.GameObjects.Other

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.Collitions.CanMoveCollition
import com.mygdx.game.Collitions.IllegalMoveCollition
import com.mygdx.game.DefaultTextureHandler
import com.mygdx.game.Enums.Layer
import com.mygdx.game.GameObjects.Sensors.SignSensor
import com.mygdx.game.Interfaces.Collition
import com.mygdx.game.Locations.DefaultLocation
import com.mygdx.game.minus

class Sign(initPosition: Vector2, size: Vector2, defaultLocation: DefaultLocation?, text:String, text2: String? = null) :
    GameObject(initPosition, size, defaultLocation) {
    override val texture = DefaultTextureHandler.getTexture("Sign.png")
    override val layer = Layer.ONGROUND
    override val collition = CanMoveCollition

    lateinit var signSensor: SignSensor

    init {
        signSensor = SignSensor(this.initPosition - Vector2(0f,40f), Vector2(size.x, size.y / 2), defaultLocation, text, text2)
        signSensor.addToLocation(defaultLocation!!)
    }

    override fun removeFromLocation() {
        signSensor.removeFromLocation()
        super.removeFromLocation()
    }
}