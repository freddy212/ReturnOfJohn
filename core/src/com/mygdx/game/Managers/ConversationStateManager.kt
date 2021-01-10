package com.mygdx.game.Managers

import com.mygdx.game.Enums.ConversationState

object ConversationStateManager {

    private var conversationState = ConversationState.BEFORE

    fun startConversation(){
        conversationState = ConversationState.ONGOING
    }
    fun resetConversation(){
        conversationState = ConversationState.BEFORE
    }
    fun getConversationState(): ConversationState{
        return conversationState
    }

}