package com.mygdx.game.GameObjects.Gates

import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.Collitions.IllegalMoveCollition
import com.mygdx.game.DefaultTextureHandler
import com.mygdx.game.Enums.Layer
import com.mygdx.game.GameObjects.Sensors.KeySensor
import com.mygdx.game.Locations.DefaultLocation
import com.mygdx.game.Saving.DefaultSaveStateHandler
import com.mygdx.game.Saving.SaveStateEntity

class LockedDoor(Position: Vector2, size: Vector2, defaultLocation: DefaultLocation?) : GameObject(Position, size, defaultLocation),
                                                                             SaveStateEntity by DefaultSaveStateHandler(){
    override val texture = DefaultTextureHandler.getTexture("LockedDoor.png")
    override val layer = Layer.ONGROUND

    override val collition = IllegalMoveCollition
    private var sensor: KeySensor

    init {
        sensor = KeySensor(Position,Vector2(100f,50f),defaultLocation!!,this)
        defaultLocation.addGameObject(sensor)
    }

    override fun removeFromLocation() {
        sensor.removeFromLocation()
        super.removeFromLocation()
    }
}