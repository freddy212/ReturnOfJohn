package com.mygdx.game.GameObjects.MoveableEntities.Projectiles

import com.badlogic.gdx.math.Circle
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.*
import com.mygdx.game.Collitions.DefaultProjectileCollition
import com.mygdx.game.DefaultTextureHandler
import com.mygdx.game.EdgeOfLocationStrategies.RemoveObject
import com.mygdx.game.Enums.Layer
import com.mygdx.game.GameObjects.MoveableEntities.IceClone
import com.mygdx.game.Locations.DefaultLocation
import com.mygdx.game.Managers.LocationManager
import com.mygdx.game.getUnitVectorTowardsPoint
import com.mygdx.game.player

class Rocket(
    Position: Vector2,
    size: Vector2,
    defaultLocation: DefaultLocation?,
    unitVectorDirection: Vector2,
    shooter: GameObject,
    val target: GameObject,
    val aggroRadius: Float = 500f,
    val speed: Float = 8f
) : Projectile(Position, size, defaultLocation, unitVectorDirection, shooter) {
    override var baseSpeed = speed
    override val movementStrategy = DefaultMovement(RemoveObject())
    override val texture = DefaultTextureHandler.getTexture("Rocket.png")
    override val layer = Layer.AIR
    override val collition: RocketCollition = RocketCollition(this)

    init {
        setRotation(unitVectorDirection, this, 0f)
    }

    override fun frameTask() {
        super.frameTask()
        val clone = LocationManager.ActiveGameObjects.find { it is IceClone }
        if (clone != null && InsideCircle(
                Circle(
                    this.sprite.x,
                    this.sprite.y,
                    aggroRadius
                ), clone
            ).ShouldBeAggroed()
        ) {
            unitVectorDirection = getUnitVectorTowardsPoint(this.currentPosition(), clone.currentPosition())
            setRotation(unitVectorDirection, this, 0f)
        }
        else if (InsideCircle(Circle(this.sprite.x, this.sprite.y, aggroRadius), target).ShouldBeAggroed()) {
            unitVectorDirection = getUnitVectorTowardsPoint(this.currentPosition(), target.currentPosition())
            setRotation(unitVectorDirection, this, 0f)
        }
    }
}

class RocketCollition(val rocket: Rocket): ProjectileCollition(rocket) {
    override fun collitionHappened(collidedObject: GameObject) {
        super.collitionHappened(collidedObject)
        if(collidedObject is Icicle){
            rocket.removeFromLocation()
            collidedObject.removeFromLocation()
        }
    }
}