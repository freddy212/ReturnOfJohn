package com.mygdx.game.Events

import com.mygdx.game.GameObjects.MoveableEntities.NPC
import com.mygdx.game.Interfaces.Event
import com.mygdx.game.UI.Dialogue.OptionSentence

class ContinueConversationEvent(val npc: NPC): Event {
    val conversationHandler = npc.conversationsHandler

    override fun execute() {
       /* val currentSentence = npc.conversationsHandler.GetSentence()
        if(currentSentence is OptionSentence){
            val textChoiceHandler = (npc.conversationsHandler.GetSentence() as OptionSentence).textChoice
            textChoiceHandler.activeOption.Event.execute()
            if(textChoiceHandler.activeOption == textChoiceHandler.Option2){
                textChoiceHandler.changeActive()
            }
        }*/
       val endOfConversation = !conversationHandler.continueConversation()
       if(endOfConversation){
           EndConversationEvent(npc).execute()
       }
    }

}