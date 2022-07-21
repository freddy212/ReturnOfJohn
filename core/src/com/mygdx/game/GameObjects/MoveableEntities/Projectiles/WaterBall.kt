package com.mygdx.game.GameObjects.MoveableEntities.Projectiles

import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.*
import com.mygdx.game.Collitions.WaterGunCollition
import com.mygdx.game.DefaultTextureHandler
import com.mygdx.game.EdgeOfLocationStrategies.RemoveObject
import com.mygdx.game.Enums.Layer
import com.mygdx.game.Locations.DefaultLocation
import com.mygdx.game.times
import com.mygdx.game.plus

class WaterBall(Position: Vector2, size: Vector2, defaultLocation: DefaultLocation?,unitVectorDirection: Vector2, shooter: GameObject) : Projectile(Position, size, defaultLocation,unitVectorDirection, shooter){
    override var currentSpeed = 8f
    override val movementStrategy = DefaultMovement(RemoveObject())
    override val texture = DefaultTextureHandler.getTexture("WaterBall.png")
    override val layer = Layer.AIR
    override val collition = WaterGunCollition()

    init {
        setRotation(unitVectorDirection,this,90f)
        val startPos = Vector2(Position.x - size.x / 2, Position.y - size.y / 2)
        val offset = unitVectorDirection * 80f
        setPosition(startPos + offset)
    }

    override fun frameTask() {
        super.frameTask()
        rotateByAmount(2f,this)
    }
}