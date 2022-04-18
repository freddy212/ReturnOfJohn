package com.mygdx.game.Events

import com.mygdx.game.GameObjects.MoveableEntities.Characters.NPC
import com.mygdx.game.Interfaces.Event
import com.mygdx.game.Managers.ConversationStateManager
import com.mygdx.game.Managers.EventManager
import com.mygdx.game.player
import java.util.function.Predicate


class EndConversationEvent(val npc: NPC): Event {
    val conversationHandler = npc.conversationsHandler
    override fun execute(){
        conversationHandler.resetConversation()

        EventManager.eventManager.removeIf { x -> x is DrawSentenceEvent }
        ConversationStateManager.resetConversation()

        npc.enableMoving()
        player.enableMoving()
        player.enableChangingDirection()
    }
}