package com.mygdx.game.Events

import com.mygdx.game.GameObjects.MoveableEntities.Characters.NPC
import com.mygdx.game.Interfaces.Event

class ContinueConversationEvent(val npc: NPC): Event {
    val conversationHandler = npc.conversationsHandler

    override fun execute() {
       val endOfConversation = !conversationHandler.continueConversation()
       if(endOfConversation){
           EndConversationEvent(npc).execute()
       }
    }

}