package com.mygdx.game.AbstractClasses

import com.badlogic.gdx.math.Vector2
import com.mygdx.game.Locations.DefaultLocation

abstract class Projectile(Position: Vector2, size: Vector2, defaultLocation: DefaultLocation?) : MoveableObject(Position, size, defaultLocation),
    RotationalObject by DefaultRotationalObject(){
    override fun frameTask() {
        super.frameTask()
        this.move(unitVectorDirection)
    }
}