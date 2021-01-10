package com.mygdx.game.Events

import com.mygdx.game.ConversationHandler
import com.mygdx.game.Interfaces.Event
import com.mygdx.game.UI.Dialogue.UIController

class DrawSentenceEvent(val conversationHandler: ConversationHandler): Event {
    override fun execute() {
       val sentence = conversationHandler.GetSentence()
       UIController.drawSentence(sentence,sentence.character)
    }
}