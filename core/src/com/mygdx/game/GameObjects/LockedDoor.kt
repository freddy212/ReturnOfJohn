package com.mygdx.game.GameObjects

import com.mygdx.game.DefaultTextureHandler
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.Collitions.IllegalMoveCollition
import com.mygdx.game.Enums.Layer
import com.mygdx.game.GameObjects.Sensors.KeySensor
import com.mygdx.game.LocationImpl
import com.mygdx.game.SaveHandling.DefaultRemoveObjectSaveState
import com.mygdx.game.SaveState.SaveStateEntity

class LockedDoor(Position: Vector2, size: Vector2, location: LocationImpl?) : GameObject(Position, size, location),
                                                                             SaveStateEntity by DefaultRemoveObjectSaveState(){
    override val texture = DefaultTextureHandler.getTexture("LockedDoor.png")
    override val layer = Layer.ONGROUND

    override val collition = IllegalMoveCollition
    private lateinit var sensor: KeySensor

    init {
        sensor = KeySensor(Position,Vector2(100f,50f),location!!,this)
        location.addGameObject(sensor)
    }

    override fun removeFromLocation() {
        sensor.removeFromLocation()
        super.removeFromLocation()
    }
}