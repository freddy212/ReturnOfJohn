package com.mygdx.game.Collitions

import com.badlogic.gdx.Input
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.Enums.getDirectionUnitVector
import com.mygdx.game.Events.ConversationEvent
import com.mygdx.game.GameObjects.MoveableEntities.Characters.Player
import com.mygdx.game.GameObjects.Sensors.TalkSensor
import com.mygdx.game.Interfaces.KeyPressedCollition
import com.mygdx.game.checkOpposingDirections

class TalkCollition(private val conversationEvent: ConversationEvent, val talkSensor: TalkSensor): KeyPressedCollition() {
    override val specificButton = Input.Keys.SPACE
    override fun collitionHappened(collidedObject: GameObject) {
        if(collidedObject is Player){
            if(checkOpposingDirections(collidedObject, talkSensor)){
                val characterRotation = getDirectionUnitVector(talkSensor.direction)
                talkSensor.npc.setCharacterRotation(characterRotation)
                conversationEvent.execute()
            }
        }
    }

    override fun renderKeyToUI(entity: GameObject, collidedObject: GameObject) {
        if(entity is TalkSensor && collidedObject is Player) {
            if (checkOpposingDirections(collidedObject, entity)) {
                super.renderKeyToUI(collidedObject,entity)
            }
        }
    }
}