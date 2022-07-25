package com.mygdx.game.InputActions

import com.badlogic.gdx.Input
import com.mygdx.game.Events.DrawSentenceEvent
import com.mygdx.game.Interfaces.InputAction
import com.mygdx.game.Managers.EventManager
import com.mygdx.game.UI.Dialogue.OptionSentence

class ChangeDialogueOption: InputAction {
    override val keycodes = listOf(Input.Keys.LEFT,Input.Keys.RIGHT,Input.Keys.A,Input.Keys.D)

    override fun action() {
        val readSentenceEvent = EventManager.eventManager.List.find {it is DrawSentenceEvent } as DrawSentenceEvent?
        if(readSentenceEvent != null){
            if(readSentenceEvent.conversationHandler.GetSentence() is OptionSentence){
                (readSentenceEvent.conversationHandler.GetSentence() as OptionSentence).textChoice.changeActive()
            }
        }
    }
}