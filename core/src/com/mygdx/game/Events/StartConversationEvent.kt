package com.mygdx.game.Events

import com.mygdx.game.GameObjects.MoveableEntities.Characters.NPC
import com.mygdx.game.Interfaces.Event
import com.mygdx.game.Managers.ConversationStateManager
import com.mygdx.game.Managers.EventManager
import com.mygdx.game.UI.Dialogue.Conversation
import com.mygdx.game.player

class StartConversationEvent(val npc: NPC): Event {
    val conversationHandler = npc.conversationsHandler
    private val readSentenceEvent = DrawSentenceEvent(npc.conversationsHandler)
    override fun execute() {
        ConversationStateManager.startConversation()
        EventManager.eventManager.add(readSentenceEvent)
        npc.freezeMoving()
        player.freezeMoving()
        player.freezeChangingDirection()
    }
}