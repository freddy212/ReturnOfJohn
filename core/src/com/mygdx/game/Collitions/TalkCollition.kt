package com.mygdx.game.Collitions

import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.Events.ConversationEvent
import com.mygdx.game.GameObjects.MoveableEntities.Player
import com.mygdx.game.GameObjects.TalkSensor
import com.mygdx.game.Interfaces.ButtonPressedCollition
import com.mygdx.game.Interfaces.DynamicEntity
import com.mygdx.game.checkOpposingDirections
import com.mygdx.game.counter

class TalkCollition(private val conversationEvent: ConversationEvent): ButtonPressedCollition{
    override fun collitionHappened(entity: DynamicEntity, collidedObject: GameObject) {
        if(entity is Player && collidedObject is TalkSensor){
            if(checkOpposingDirections(entity, collidedObject)){
                counter += 1
                println("Collition is successfull " + counter)
                conversationEvent.execute()
            }
        }
    }
}