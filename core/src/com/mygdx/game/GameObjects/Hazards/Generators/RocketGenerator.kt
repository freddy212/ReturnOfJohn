package com.mygdx.game.GameObjects.Hazards.Generators

import com.badlogic.gdx.math.Circle
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.*
import com.mygdx.game.AbstractClasses.*
import com.mygdx.game.GameObjects.MoveableEntities.IceClone
import com.mygdx.game.GameObjects.MoveableEntities.Projectiles.Rocket
import com.mygdx.game.Locations.DefaultLocation
import com.mygdx.game.Managers.LocationManager

class RocketGenerator(Position: Vector2, size: Vector2, unitVectorDirection: Vector2, defaultLocation: DefaultLocation,
                   timeUntilFire: Float = 0f, shootCoolDown:Float = 3f, val aggroRadius:Float = 500f, val speed: Float = 8f):
    Generator(Position, size,defaultLocation,unitVectorDirection,timeUntilFire,shootCoolDown),
    AggroableEntity by DefaultAggroableEntity(){

    override fun generateProjectile(): Projectile{
        val Position = Vector2(this.sprite.x,this.sprite.y) + getDistanceFromGenerator(unitVectorDirection)
        val rocket = Rocket(Position,Vector2(100f,34f),defaultLocation,Vector2(unitVectorDirection.x,unitVectorDirection.y), this, player, aggroRadius, speed)
        return rocket
    }

    override fun frameTask() {
        val clone = LocationManager.ActiveGameObjects.find { it is IceClone }
        val playerInside = InsideCircle(Circle(this.sprite.x, this.sprite.y, aggroRadius)).ShouldBeAggroed()
        val cloneInside = if(clone == null) false else InsideCircle(Circle(this.sprite.x, this.sprite.y, aggroRadius),clone).ShouldBeAggroed()
        shouldFire = playerInside || cloneInside
        super.frameTask()
    }
}