package com.mygdx.game.Collitions

import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.AbstractClasses.MoveableObject
import com.mygdx.game.Enums.Direction
import com.mygdx.game.Enums.getDirectionUnitVector
import com.mygdx.game.GameObjects.MoveableEntities.Characters.Player
import com.mygdx.game.GameObjects.MoveableEntities.IceClone
import com.mygdx.game.Interfaces.Event
import com.mygdx.game.Interfaces.AreaEntranceCollition
import com.mygdx.game.Interfaces.DefaultAreaEntranceCollition
import com.mygdx.game.Managers.EventManager
import com.mygdx.game.player
import com.mygdx.game.times

enum class ConveyerBeltStrength{NORMAL, STRONG}

class HandleConveyerBeltEvent(val direction: Direction, val moveableObject: MoveableObject,val conveyerBeltStrength: ConveyerBeltStrength) : Event {
    override fun execute() {
        if(moveableObject is Player && this in EventManager.eventManager.List){
            val movementAmount = if(conveyerBeltStrength == ConveyerBeltStrength.NORMAL)  Vector2(getDirectionUnitVector(direction) * player.baseSpeed) else  Vector2(getDirectionUnitVector(direction) * player.baseSpeed * 1.5f)
            player.moveModifier = movementAmount
            if (!player.hasMovedThisFrame) {
                player.move(Vector2(0f, 0f))
                player.setCharacterRotation(getDirectionUnitVector(direction))
            }
        }

        if(moveableObject is IceClone){
            if(moveableObject !in player.defaultLocation!!.gameObjects.List){
                EventManager.eventManager.remove(this)
            } else {
                moveableObject.moveModifier = Vector2(getDirectionUnitVector(direction) * (player.baseSpeed / 2))
                moveableObject.move(Vector2(0f,0f))
            }

        }
    }

}

class ConveyerBeltCollition(val direction: Direction, val conveyerBeltStrength: ConveyerBeltStrength) : DefaultAreaEntranceCollition() {
    val handleConveyerBeltEvent = HandleConveyerBeltEvent(direction, player, conveyerBeltStrength)
    var handleIceCloneConveyerBeltEvent: HandleConveyerBeltEvent? = null

    override fun collitionHappened(collidedObject: GameObject) {
        if(collidedObject is Player || collidedObject is IceClone){
            movedInside(collidedObject)
        }
    }

    override fun movedInsideAction(objectEntered: GameObject) {
        if(objectEntered is Player){
            EventManager.eventManager.add(handleConveyerBeltEvent)
        } else if (objectEntered is IceClone){
            handleIceCloneConveyerBeltEvent = HandleConveyerBeltEvent(direction, objectEntered, conveyerBeltStrength)
            EventManager.eventManager.add(handleIceCloneConveyerBeltEvent!!)
        }
    }

    override fun movedOutsideAction(objectLeaved: GameObject) {
        if(objectLeaved is Player){
            EventManager.eventManager.remove(handleConveyerBeltEvent)
            player.moveModifier = Vector2(0f,0f)
        }
        else if(objectLeaved is IceClone){
            EventManager.eventManager.remove(handleIceCloneConveyerBeltEvent!!)
        }
    }
}