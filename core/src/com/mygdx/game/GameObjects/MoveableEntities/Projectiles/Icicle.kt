package com.mygdx.game.GameObjects.MoveableEntities.Projectiles

import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.DefaultMovement
import com.mygdx.game.AbstractClasses.Projectile
import com.mygdx.game.Collitions.DefaultProjectileCollition
import com.mygdx.game.DefaultTextureHandler
import com.mygdx.game.EdgeOfLocationStrategies.RemoveObject
import com.mygdx.game.Enums.Layer
import com.mygdx.game.Locations.DefaultLocation

class Icicle(Position: Vector2, size: Vector2, defaultLocation: DefaultLocation?,unitVectorDirection: Vector2) : Projectile(Position, size, defaultLocation,unitVectorDirection) {
    override var currentSpeed = 8f
    override val movementStrategy = DefaultMovement(RemoveObject())
    override val texture = DefaultTextureHandler.getTexture("Icicle.png")
    override val layer = Layer.AIR
    override val collition = DefaultProjectileCollition()
    init {
        setRotation(unitVectorDirection,this,0f)
    }
}