package com.mygdx.game.Events

import com.mygdx.game.Enums.ConversationState
import com.mygdx.game.GameObjects.MoveableEntities.Characters.NPC
import com.mygdx.game.Interfaces.Event
import com.mygdx.game.Managers.ConversationStateManager
import com.mygdx.game.UI.Dialogue.Conversation

class ConversationEvent(private val convo: Conversation, private val npc: NPC): Event {
    override fun execute() {
        when(ConversationStateManager.getConversationState()){
            ConversationState.BEFORE -> {StartConversationEvent(npc).execute()}
            ConversationState.ONGOING -> npc.conversationsHandler.GetSentence().dialogueText.event.execute()
        }
    }
}