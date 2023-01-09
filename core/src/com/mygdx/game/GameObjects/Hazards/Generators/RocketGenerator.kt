package com.mygdx.game.GameObjects.Hazards.Generators

import com.badlogic.gdx.math.Circle
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.*
import com.mygdx.game.AbstractClasses.*
import com.mygdx.game.GameObjects.MoveableEntities.Projectiles.Rocket
import com.mygdx.game.Locations.DefaultLocation

class RocketGenerator(Position: Vector2, size: Vector2, unitVectorDirection: Vector2, defaultLocation: DefaultLocation,
                   timeUntilFire: Float = 0f, shootCoolDown:Float = 3f, val aggroRadius:Float = 400f):
    Generator(Position, size,defaultLocation,unitVectorDirection,timeUntilFire,shootCoolDown),
    AggroableEntity by DefaultAggroableEntity(){

    override fun generateProjectile(): Projectile{
        val Position = Vector2(this.sprite.x,this.sprite.y) + getDistanceFromGenerator(unitVectorDirection)
        val rocket = Rocket(Position,Vector2(100f,34f),defaultLocation,Vector2(unitVectorDirection.x,unitVectorDirection.y), this, player)
        return rocket
    }

    override fun frameTask() {
        val insideCircle = InsideCircle(Circle(this.sprite.x, this.sprite.y, aggroRadius)).ShouldBeAggroed()
        shouldFire = insideCircle
        super.frameTask()
    }
}