package com.mygdx.game.GameObjects.MoveableEntities.Projectiles
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.DefaultMovement
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.AbstractClasses.Projectile
import com.mygdx.game.AbstractClasses.ProjectileCollition
import com.mygdx.game.Collitions.DefaultProjectileCollition
import com.mygdx.game.DefaultTextureHandler
import com.mygdx.game.EdgeOfLocationStrategies.RemoveObject
import com.mygdx.game.Enums.Layer
import com.mygdx.game.GameObjects.MoveableEntities.IceClone
import com.mygdx.game.Locations.DefaultLocation
import com.mygdx.game.Managers.LocationManager
import com.mygdx.game.getUnitVectorTowardsPoint

class Rocket(Position: Vector2, size: Vector2, defaultLocation: DefaultLocation?, unitVectorDirection: Vector2, shooter: GameObject, val target: GameObject) : Projectile(Position, size, defaultLocation,unitVectorDirection, shooter) {
    override var baseSpeed = 6f
    override val movementStrategy = DefaultMovement(RemoveObject())
    override val texture = DefaultTextureHandler.getTexture("Rocket.png")
    override val layer = Layer.AIR
    override val collition: ProjectileCollition = DefaultProjectileCollition()
    init {
        setRotation(unitVectorDirection,this,0f)
    }

    override fun frameTask() {
        super.frameTask()
        val clone = LocationManager.newDefaultLocation.gameObjects.find { it is IceClone }
        val newTarget = clone ?: target
        unitVectorDirection = getUnitVectorTowardsPoint(this.currentPosition(), newTarget.currentPosition())
        setRotation(unitVectorDirection,this, 0f)
    }
}