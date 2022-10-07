package com.mygdx.game.GameObjects.MoveableEntities.Projectiles
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.DefaultMovement
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.AbstractClasses.Projectile
import com.mygdx.game.AbstractClasses.ProjectileCollition
import com.mygdx.game.Collitions.CanMoveCollition
import com.mygdx.game.Collitions.DefaultProjectileCollition
import com.mygdx.game.Collitions.FireballCollition
import com.mygdx.game.DefaultTextureHandler
import com.mygdx.game.EdgeOfLocationStrategies.RemoveObject
import com.mygdx.game.Enums.Layer
import com.mygdx.game.Locations.DefaultLocation
import com.mygdx.game.getUnitVectorTowardsPoint
import com.mygdx.game.unitVectorToAngle

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
        unitVectorDirection = getUnitVectorTowardsPoint(this.currentPosition(), target.currentPosition())
        setRotation(unitVectorDirection,this, 0f)
    }
}