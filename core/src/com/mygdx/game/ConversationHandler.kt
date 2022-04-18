package com.mygdx.game

import com.mygdx.game.UI.Dialogue.Conversation
import com.mygdx.game.UI.Dialogue.Sentence

class ConversationHandler(firstConvo: Conversation, identifier: String) {
    private val conversations = mutableMapOf<String,Conversation>()
    private var currentStep = 0
    private var activeConversation: Conversation

    init {
        conversations[identifier] = firstConvo
        activeConversation = firstConvo
    }
    fun addConversation(conversationIdentifier: String, conversation: Conversation){
        conversations[conversationIdentifier] = conversation
    }
    fun getConversation(conversationIdentifier: String): Conversation{
        return conversations[conversationIdentifier]!!
    }
    fun getActiveConversation(): Conversation{
        return activeConversation
    }
    fun setConversation(identifier: String){
        activeConversation = conversations[identifier]!!
    }
    fun continueConversation():Boolean{
        if(activeConversation.sentenceList.size > currentStep + 1){
            currentStep += 1
            return true
        }
        return false
    }
    fun GetSentence():Sentence{
        return activeConversation.sentenceList[currentStep]
    }
    fun resetConversation(){
        currentStep = 0
    }
}