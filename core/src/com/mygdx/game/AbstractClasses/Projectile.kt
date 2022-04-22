package com.mygdx.game.AbstractClasses

import com.badlogic.gdx.math.Vector2
import com.mygdx.game.Locations.DefaultLocation

abstract class Projectile(Position: Vector2, size: Vector2, defaultLocation: DefaultLocation?,
                          override var unitVectorDirection: Vector2) : MoveableObject(Position, size, defaultLocation),
    RotationalObject by DefaultRotationalObject(){
    init {
        onLocationExitActions.add(::removeProjectile)
    }
    override fun frameTask() {
        super.frameTask()
        this.move(unitVectorDirection)
    }

    fun removeProjectile(newLocation:DefaultLocation){
        this.removeFromLocation()
    }
}