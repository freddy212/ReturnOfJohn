package com.mygdx.game.GameObjects.MoveableEntities.Projectiles

import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.DefaultMovement
import com.mygdx.game.AbstractClasses.Projectile
import com.mygdx.game.Collitions.BoulderCollition
import com.mygdx.game.DefaultTextureHandler
import com.mygdx.game.EdgeOfLocationStrategies.RemoveObject
import com.mygdx.game.Enums.Layer
import com.mygdx.game.Locations.DefaultLocation
import com.mygdx.game.middleOfObject

class Boulder(override var unitVectorDirection: Vector2, Position: Vector2, size: Vector2, defaultLocation: DefaultLocation?):
        Projectile(middleOfObject(Position,size),size,defaultLocation){
    override val movementStrategy = DefaultMovement(RemoveObject())
    override val texture = DefaultTextureHandler.getTexture("Boulder.png")
    override var currentSpeed = 5f
    override val layer = Layer.AIR
    override val collition = BoulderCollition()
}