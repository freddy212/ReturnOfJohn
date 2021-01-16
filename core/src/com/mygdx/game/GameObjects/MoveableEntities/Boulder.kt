package com.mygdx.game.GameObjects.MoveableEntities

import com.mygdx.game.DefaultTextureHandler
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.DefaultMovement
import com.mygdx.game.AbstractClasses.MoveableObject
import com.mygdx.game.Collitions.BoulderCollition
import com.mygdx.game.EdgeOfLocationStrategies.RemoveObject
import com.mygdx.game.Enums.Direction
import com.mygdx.game.Enums.Layer
import com.mygdx.game.Enums.getDirectionUnitVector
import com.mygdx.game.LocationImpl
import com.mygdx.game.getDirectionFromAngle
import com.mygdx.game.middleOfObject

class Boulder(var directionGiven: Vector2, Position: Vector2, size: Vector2,location: LocationImpl?):
        MoveableObject(middleOfObject(Position,size),size,location){
    override val movementStrategy = DefaultMovement(RemoveObject())
    override val texture = DefaultTextureHandler.getTexture("Boulder.png")
    override var currentSpeed = 5f
    override val layer = Layer.AIR

    override fun frameTask() {
        super.frameTask()
        this.move(directionGiven)
    }

    override val collition = BoulderCollition()
}