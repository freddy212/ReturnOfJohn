package com.mygdx.game.Events

import com.mygdx.game.Enums.ConversationState
import com.mygdx.game.GameObjects.MoveableEntities.NPC
import com.mygdx.game.Interfaces.Event

class ConversationEvent(private val identifier: String, private val npc: NPC): Event {
    private var conversationState = ConversationState.BEFORE
    private val readSentenceEvent = DrawSentenceEvent(npc.conversationsHandler)
    override fun execute() {
        when(conversationState){
            ConversationState.BEFORE -> {StartConversationEvent(identifier,npc,readSentenceEvent).execute()
                                         conversationState = ConversationState.ONGOING}
            ConversationState.ONGOING -> ContinueConversationEvent(npc,readSentenceEvent,this).execute()
        }
    }
    fun resetConversation(){
        conversationState = ConversationState.BEFORE
    }

}