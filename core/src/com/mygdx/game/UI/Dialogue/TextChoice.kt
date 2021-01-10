package com.mygdx.game.UI.Dialogue

import com.mygdx.game.GameObjects.MoveableEntities.NPC
import com.mygdx.game.Interfaces.Event

class TextChoice(val Option1: DialogueChoice, val Option2: DialogueChoice,npc:NPC):DialogueText(Option1.Option + Option2.Option,npc) {
    var activeOption = Option1
        private set
    override var event: Event = object: Event{
        override fun execute() {
            activeOption.event.execute()
            if(activeOption == Option2){
                changeActive()
            }
        }
    }
    fun changeActive(){
        if(activeOption == Option1){
            activeOption = Option2
        }else{
            activeOption = Option1
        }
    }
}
