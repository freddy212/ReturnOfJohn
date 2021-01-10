package com.mygdx.game.GameObjects

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.Collitions.IllegalMoveCollition
import com.mygdx.game.Enums.Layer
import com.mygdx.game.GameObjects.Sensors.KeySensor
import com.mygdx.game.LocationImpl

class LockedDoor(Position: Vector2, size: Vector2, location: LocationImpl?) : GameObject(Position, size, location) {
    override val texture = Texture("LockedDoor.png")
    override val layer = Layer.ONGROUND

    override val collition = IllegalMoveCollition

    init {
        val sensor = KeySensor(Position,Vector2(100f,50f),location!!,this)
        location.addGameObject(sensor)
    }
}