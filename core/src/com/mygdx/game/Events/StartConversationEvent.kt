package com.mygdx.game.Events

import com.mygdx.game.GameObjects.MoveableEntities.NPC
import com.mygdx.game.Interfaces.Event
import com.mygdx.game.Managers.EventManager
import com.mygdx.game.player

class StartConversationEvent(val identifier: String,val npc: NPC,val readSentenceEvent: DrawSentenceEvent): Event {
    val conversationHandler = npc.conversationsHandler
    override fun execute() {
        conversationHandler.startConversation(identifier)
        EventManager.eventManager.add(readSentenceEvent)
        npc.freezeMoving()
        player.freezeMoving()
        player.freezeChangingDirection()
    }
}