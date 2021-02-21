package com.mygdx.game.AbstractClasses

import com.badlogic.gdx.math.Vector2
import com.mygdx.game.LocationImpl

abstract class Projectile(Position: Vector2, size: Vector2, location: LocationImpl?) : MoveableObject(Position, size, location),
    RotationalObject by DefaultRotationalObject(){
    override fun frameTask() {
        super.frameTask()
        this.move(unitVectorDirection)
    }
}