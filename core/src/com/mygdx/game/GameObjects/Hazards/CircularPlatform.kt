package com.mygdx.game.GameObjects.Hazards

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.DefaultMovement
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.AbstractClasses.MoveableObject
import com.mygdx.game.Collitions.HandleConveyerBeltEvent
import com.mygdx.game.Collitions.RemoveDotDamageCollition
import com.mygdx.game.DefaultTextureHandler
import com.mygdx.game.EdgeOfLocationStrategies.MoveRegardless
import com.mygdx.game.EdgeOfLocationStrategies.NoAction
import com.mygdx.game.Enums.Layer
import com.mygdx.game.Enums.getDirectionUnitVector
import com.mygdx.game.GameObjects.MoveableEntities.Characters.Player
import com.mygdx.game.Interfaces.*
import com.mygdx.game.Locations.DefaultLocation
import com.mygdx.game.Managers.EventManager
import com.mygdx.game.circularMove
import com.mygdx.game.player

class CircularMovementEvent(val platform: CircularPlatform) : Event {
    override fun execute() {
        player.moveModifier = platform.unitVectorDirection
        if (!player.hasMovedThisFrame) {
            player.move(Vector2(0f, 0f))
        }
    }

}

class CircularPlatform(Position: Vector2, size: Vector2, defaultLocation: DefaultLocation, val startAngle: Float = 0f) :
    MoveableObject(Position, size, defaultLocation) {
    override var baseSpeed = 1f
    override val movementStrategy = DefaultMovement(MoveRegardless())
    override var unitVectorDirection = Vector2(0f, 0f)
    override val texture = DefaultTextureHandler.getTexture("MainB.jpg")
    override val layer = Layer.ONGROUND

    val removeDotDamageCollition = RemoveDotDamageCollition(defaultLocation.collition)
    override val collition = CircularPlatformCollition(removeDotDamageCollition, this)

    val increment = 1f
    var angle = startAngle;

    init {
        onLocationEnterActions.add(::resetPlatform)
    }
    override fun frameTask() {
        val oldAngle = angle
        angle = (angle + increment) % 360
        circularMove(100f,  oldAngle, angle)
        super.frameTask()
    }

    fun resetPlatform(){
        setPosition(initPosition)
        angle = startAngle
    }
}

class CircularPlatformCollition(val collition: RemoveDotDamageCollition, val platform: CircularPlatform) :
    DefaultAreaEntranceCollition() {


    val movePlayerEvent = CircularMovementEvent(platform)
    override fun collitionHappened(collidedObject: GameObject) {
        collition.collitionHappened(collidedObject)
        super.collitionHappened(collidedObject)
    }

    override fun movedInsideAction(objectEntered: GameObject) {
        if (objectEntered is Player) {
            EventManager.eventManager.add(movePlayerEvent)
        }
    }

    override fun movedOutsideAction(objectLeaved: GameObject) {
        if (objectLeaved is Player) {
            EventManager.eventManager.remove(movePlayerEvent)
        }
    }

    override fun filterCollitions(gameObjects: List<GameObject>): List<GameObject> {
        return collition.filterCollitions(gameObjects)
    }

}