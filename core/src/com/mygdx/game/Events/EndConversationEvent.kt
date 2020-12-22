package com.mygdx.game.Events

import com.mygdx.game.GameObjects.MoveableEntities.NPC
import com.mygdx.game.Interfaces.Event
import com.mygdx.game.Managers.EventManager
import com.mygdx.game.player


class EndConversationEvent(val npc: NPC, val readSentenceEvent: DrawSentenceEvent, val conversationEvent: ConversationEvent): Event {
    val conversationHandler = npc.conversationsHandler
    override fun execute(){
        conversationHandler.resetConversation()

        EventManager.eventManager.remove(readSentenceEvent)

        conversationEvent.resetConversation()

        npc.enableMoving()
        player.enableMoving()
        player.enableChangingDirection()
    }
}