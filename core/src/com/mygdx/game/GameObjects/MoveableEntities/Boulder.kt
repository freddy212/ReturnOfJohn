package com.mygdx.game.GameObjects.MoveableEntities

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.DefaultMovement
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.AbstractClasses.MoveableObject
import com.mygdx.game.Collitions.BoulderPlayerCollition
import com.mygdx.game.EdgeOfLocationStrategies.RemoveObject
import com.mygdx.game.Enums.Direction
import com.mygdx.game.Enums.Layer
import com.mygdx.game.Interfaces.MoveableEntity
import com.mygdx.game.Interfaces.MovementStrategy
import com.mygdx.game.LocationImpl
import com.mygdx.game.middleOfObject

class Boulder(directionGiven: Direction, Position: Vector2, size: Vector2,location: LocationImpl?):
        MoveableObject(middleOfObject(Position,size),size,location){
    override val movementStrategy = DefaultMovement(RemoveObject())
    override val texture = Texture("Boulder.png")
    override var speed = 2f
    override var direction = directionGiven
    override val layer = Layer.AIR

    override fun frameTask() {
        super.frameTask()
        this.move(direction)
    }

    override val collition = BoulderPlayerCollition()
}