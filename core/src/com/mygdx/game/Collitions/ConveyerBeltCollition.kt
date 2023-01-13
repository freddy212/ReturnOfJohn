package com.mygdx.game.Collitions

import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.Enums.Direction
import com.mygdx.game.Enums.getDirectionUnitVector
import com.mygdx.game.GameObjects.MoveableEntities.Characters.Player
import com.mygdx.game.Interfaces.Event
import com.mygdx.game.Interfaces.AreaEntranceCollition
import com.mygdx.game.Interfaces.DefaultAreaEntranceCollition
import com.mygdx.game.Managers.EventManager
import com.mygdx.game.player
import com.mygdx.game.times

class ConveyerBeltCollition(val direction: Direction): AreaEntranceCollition by DefaultAreaEntranceCollition() {
    object ResetModifier : Event {
        override fun execute() {
            player.moveModifier = Vector2(0f,0f)
        }

        override fun runOnce(): Boolean {
            return true
        }
    }
    override fun collitionHappened(collidedObject: GameObject) {
        if(collidedObject is Player){
            player.moveModifier = Vector2(getDirectionUnitVector(direction) * player.baseSpeed)
            if(!player.hasMovedThisFrame){
                player.move(Vector2(0f,0f))
                player.setCharacterRotation(getDirectionUnitVector(direction))
            }
            EventManager.eventManager.add(ResetModifier)
        }
    }
}