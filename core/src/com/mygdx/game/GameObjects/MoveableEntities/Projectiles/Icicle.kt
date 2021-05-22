package com.mygdx.game.GameObjects.MoveableEntities.Projectiles

import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.DefaultMovement
import com.mygdx.game.AbstractClasses.Projectile
import com.mygdx.game.Collitions.CanMoveCollition
import com.mygdx.game.DefaultTextureHandler
import com.mygdx.game.EdgeOfLocationStrategies.RemoveObject
import com.mygdx.game.Enums.Layer
import com.mygdx.game.Locations.DefaultLocation

class Icicle(Position: Vector2, size: Vector2, defaultLocation: DefaultLocation?, override val unitVectorDirection: Vector2) : Projectile(Position, size, defaultLocation) {
    override var currentSpeed = 8f
    override val movementStrategy = DefaultMovement(RemoveObject())
    override val texture = DefaultTextureHandler.getTexture("Icicle.png")
    override val layer = Layer.AIR
    override val collition = CanMoveCollition

    init {
        setRotation(unitVectorDirection,this,0f)
    }
}