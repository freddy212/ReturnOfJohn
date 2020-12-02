package com.mygdx.game

import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.mygdx.game.UI.Dialogue.Conversation
import com.mygdx.game.UI.Dialogue.Sentence
import com.mygdx.game.UI.Dialogue.UIController

class ConversationHandler() {
    private val conversations = mutableMapOf<String,Conversation>()
    private var currentStep = 0
    private lateinit var activeConversation: Conversation

    fun addConversation(conversationIdentifier: String, conversation: Conversation){
        conversations[conversationIdentifier] = conversation
    }
    fun getConversation(conversationIdentifier: String): Conversation{
        return conversations[conversationIdentifier]!!
    }
    fun startConversation(identifier: String){
        activeConversation = conversations[identifier]!!
    }
    //Returns true if there are more sentences left
    fun continueConversation():Boolean{
        if(activeConversation.conversation.size > currentStep + 1){
            currentStep += 1
            return true
        }
        return false
    }
    fun GetSentence():Sentence{
        return activeConversation.conversation[currentStep]
    }
    fun resetConversation(){
        currentStep = 0
    }
}