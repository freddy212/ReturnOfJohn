package com.mygdx.game.GameObjects.MoveableEntities

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.DefaultMovement
import com.mygdx.game.AbstractClasses.MoveableObject
import com.mygdx.game.Collitions.DojoMasterCollition
import com.mygdx.game.EdgeOfLocationStrategies.MoveRegardless
import com.mygdx.game.EdgeOfLocationStrategies.NoAction
import com.mygdx.game.EdgeOfLocationStrategies.RemoveObject
import com.mygdx.game.Enums.Direction
import com.mygdx.game.Enums.Layer
import com.mygdx.game.Events.DojoEvent
import com.mygdx.game.Interfaces.MoveCollition
import com.mygdx.game.Interfaces.MovementStrategy
import com.mygdx.game.LocationImpl
import com.mygdx.game.Timer.DefaultTimer
import com.mygdx.game.middleOfObject

class DojoAttackObject(directionGiven: Direction, Position: Vector2, location: LocationImpl?,size: Vector2 = Vector2(128f,128f)):
        MoveableObject(middleOfObject(Position,size),size,location) {
    override var speed = 5f
    override var direction = directionGiven
    override val movementStrategy = DefaultMovement(MoveRegardless())
    override val texture = Texture("DefaultPerson.png")
    override val layer = Layer.PERSON
    override val collition = DojoMasterCollition(DojoEvent(location!!,DefaultTimer(1f)))

    override fun frameTask() {
        super.frameTask()
        move(direction)
    }
}