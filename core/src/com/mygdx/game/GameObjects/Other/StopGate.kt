package com.mygdx.game.GameObjects.Other

import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.Collitions.IllegalMoveCollition
import com.mygdx.game.DefaultTextureHandler
import com.mygdx.game.Enums.Layer
import com.mygdx.game.Interfaces.ButtonGate
import com.mygdx.game.Locations.DefaultLocation

class StopGate(Position: Vector2, size: Vector2, val location: DefaultLocation) : GameObject(Position, size, location), ButtonGate {
    override val texture = DefaultTextureHandler.getTexture("StopGate.png")
    override val layer = Layer.ONGROUND
    override val collition = IllegalMoveCollition
    override fun buttonPressed() {
        this.removeFromLocation()
        println("here")
    }

    override fun buttonReleased() {
        this.addToLocation(location)
    }
}