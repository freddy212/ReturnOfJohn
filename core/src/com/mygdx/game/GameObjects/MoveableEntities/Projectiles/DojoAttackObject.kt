package com.mygdx.game.GameObjects.MoveableEntities.Projectiles

import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.DefaultMovement
import com.mygdx.game.AbstractClasses.MoveableObject
import com.mygdx.game.AbstractClasses.Projectile
import com.mygdx.game.Collitions.DojoMasterCollition
import com.mygdx.game.DefaultTextureHandler
import com.mygdx.game.EdgeOfLocationStrategies.MoveRegardless
import com.mygdx.game.Enums.Direction
import com.mygdx.game.Enums.Layer
import com.mygdx.game.Enums.getDirectionUnitVector
import com.mygdx.game.Events.DojoEvent
import com.mygdx.game.Locations.DefaultLocation
import com.mygdx.game.middleOfObject

class DojoAttackObject(directionGiven: Direction, Position: Vector2, defaultLocation: DefaultLocation?, dojoEvent: DojoEvent, size: Vector2 = Vector2(128f,128f)):
        MoveableObject(middleOfObject(Position,size),size,defaultLocation) {
    override var currentSpeed = 5f
    override var unitVectorDirection = getDirectionUnitVector(directionGiven)
    override val movementStrategy = DefaultMovement(MoveRegardless())
    override val texture = DefaultTextureHandler.getTexture("DefaultPerson.png")
    override val layer = Layer.PERSON
    override val collition = DojoMasterCollition(dojoEvent)

    override fun frameTask() {
        super.frameTask()
        move(unitVectorDirection)
    }
}