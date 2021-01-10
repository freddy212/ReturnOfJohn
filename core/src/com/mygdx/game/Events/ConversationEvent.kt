package com.mygdx.game.Events

import com.mygdx.game.Enums.ConversationState
import com.mygdx.game.GameObjects.MoveableEntities.NPC
import com.mygdx.game.Interfaces.Event
import com.mygdx.game.Managers.ConversationStateManager

class ConversationEvent(private val identifier: String, private val npc: NPC): Event {
    override fun execute() {
        when(ConversationStateManager.getConversationState()){
            ConversationState.BEFORE -> {StartConversationEvent(identifier,npc).execute()}
            ConversationState.ONGOING -> npc.conversationsHandler.GetSentence().dialogueText.event.execute()
        }
    }
}