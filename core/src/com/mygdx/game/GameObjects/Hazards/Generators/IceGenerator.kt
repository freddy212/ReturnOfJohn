package com.mygdx.game.GameObjects.Hazards.Generators

import com.badlogic.gdx.math.Vector2
import com.mygdx.game.*
import com.mygdx.game.AbstractClasses.*
import com.mygdx.game.GameObjects.MoveableEntities.Projectiles.Icicle
import com.mygdx.game.Locations.DefaultLocation

class IceGenerator(Position: Vector2, size: Vector2, unitVectorDirection: Vector2, defaultLocation: DefaultLocation,
                       timeUntilFire: Float = 0f, shootCoolDown:Float = 3f):
    Generator(Position, size,defaultLocation,unitVectorDirection,timeUntilFire,shootCoolDown,false){

    override fun generateProjectile(): Projectile{
        val Position = Vector2(this.sprite.x,this.sprite.y) + getDistanceFromGenerator(unitVectorDirection)
        val icicle = Icicle(Position,Vector2(100f,34f),defaultLocation,Vector2(unitVectorDirection.x,unitVectorDirection.y), this)
        return icicle
    }
}