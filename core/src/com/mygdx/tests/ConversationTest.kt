package com.mygdx.tests

import com.badlogic.gdx.math.Vector2
import com.mygdx.game.Events.ConversationEvent
import com.mygdx.game.GameObjects.MoveableEntities.NPC
import com.mygdx.game.UI.Dialogue.Conversations.GetFirstConversation
import com.mygdx.game.UI.Dialogue.Conversations.GetSecondConversation
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class ConversationTest: TestGame() {
    private val npc = NPC(Vector2(0f,0f), Vector2(0f,0f),location)
    private val firstConversation = GetFirstConversation(npc)
    private val secondConversation = GetSecondConversation(npc)
    private val conversationEvent = ConversationEvent("start",npc)
    init {
        npc.conversationsHandler.addConversation("start",firstConversation)
        npc.conversationsHandler.addConversation("second",secondConversation)
    }
    @Test
    fun TestStartConversation(){
        conversationEvent.execute()
        val sentence = npc.conversationsHandler.GetSentence()
        Assertions.assertEquals("hello, what are you doing here?",sentence.dialogueText.text)
        conversationEvent.execute()
        Assertions.assertEquals(  "I'm going for a walk",npc.conversationsHandler.GetSentence().dialogueText.text)
        conversationEvent.execute()
        Assertions.assertEquals("I see. Let me join you",npc.conversationsHandler.GetSentence().dialogueText.text)
        conversationEvent.execute()
        Assertions.assertEquals("hello, what are you doing here?",npc.conversationsHandler.GetSentence().dialogueText.text)
        conversationEvent.execute()
        conversationEvent.execute()
        npc.conversationsHandler
    }
}