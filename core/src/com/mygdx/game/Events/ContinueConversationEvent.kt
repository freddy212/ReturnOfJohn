package com.mygdx.game.Events

import com.mygdx.game.GameObjects.MoveableEntities.NPC
import com.mygdx.game.Interfaces.Event

class ContinueConversationEvent(val npc: NPC, val readSentenceEvent: DrawSentenceEvent, val conversationEvent: ConversationEvent): Event {
    val conversationHandler = npc.conversationsHandler

    override fun execute() {
       val endOfConversation = !conversationHandler.continueConversation()
       if(endOfConversation){
           EndConversationEvent(npc,readSentenceEvent,conversationEvent).execute()
       }
    }

}