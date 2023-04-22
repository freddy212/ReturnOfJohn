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

class HandleConveyerBeltEvent(val direction: Direction, val moveableObject: MoveableObject) : Event {
    override fun execute() {
        if(moveableObject is Player){
            player.moveModifier = Vector2(getDirectionUnitVector(direction) * player.baseSpeed)
            if (!player.hasMovedThisFrame) {
                player.move(Vector2(0f, 0f))
                player.setCharacterRotation(getDirectionUnitVector(direction))
            }
        }

        if(moveableObject is IceClone){
            moveableObject.moveModifier = Vector2(getDirectionUnitVector(direction) * (player.baseSpeed / 2))
            moveableObject.move(Vector2(0f,0f))
            println("here")

            if(moveableObject !in player.defaultLocation!!.gameObjects.List){
                EventManager.eventManager.remove(this)
            }
        }
    }

}

class ConveyerBeltCollition(val direction: Direction) : DefaultAreaEntranceCollition() {
    val handleConveyerBeltEvent = HandleConveyerBeltEvent(direction, player)

    override fun collitionHappened(collidedObject: GameObject) {
        if(collidedObject is Player || collidedObject is IceClone){
            movedInside(collidedObject)
            if(collidedObject is IceClone){
                println("here IceClone")
            }
            if(collidedObject is Player){
            }
        }
    }

    override fun movedInsideAction(objectEntered: GameObject) {
        if(objectEntered is Player){
            EventManager.eventManager.add(handleConveyerBeltEvent)
        } else if (objectEntered is IceClone){
            println("is ice clone")
            EventManager.eventManager.add(HandleConveyerBeltEvent(direction, objectEntered))
        }
    }

    override fun movedOutsideAction(objectLeaved: GameObject) {
        if(objectLeaved is Player){
            EventManager.eventManager.remove(handleConveyerBeltEvent)
            player.moveModifier = Vector2(0f,0f)
        }
    }
}