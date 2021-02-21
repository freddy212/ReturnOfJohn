package com.mygdx.game.Collitions

import com.badlogic.gdx.Input
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.Enums.getDirectionUnitVector
import com.mygdx.game.Events.ConversationEvent
import com.mygdx.game.GameObjects.MoveableEntities.Player
import com.mygdx.game.GameObjects.Sensors.TalkSensor
import com.mygdx.game.Interfaces.KeyPressedCollition
import com.mygdx.game.checkOpposingDirections
import com.mygdx.game.counter

class TalkCollition(private val conversationEvent: ConversationEvent): KeyPressedCollition{
    override val specificButton = Input.Keys.SPACE
    override fun collitionHappened(entity: GameObject, collidedObject: GameObject) {
        if(entity is Player && collidedObject is TalkSensor){
            if(checkOpposingDirections(entity, collidedObject)){
                val characterRotation = getDirectionUnitVector(collidedObject.direction)
                collidedObject.npc.setCharacterRotation(characterRotation)
                conversationEvent.execute()
            }
        }
    }
}