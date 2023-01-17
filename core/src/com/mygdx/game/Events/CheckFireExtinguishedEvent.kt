package com.mygdx.game.Events

import com.mygdx.game.Enums.QuestIdentifier
import com.mygdx.game.GameObjects.MoveableEntities.Characters.NPC
import com.mygdx.game.Interfaces.Event
import com.mygdx.game.Interfaces.ObjectProperty
import com.mygdx.game.ObjectProperties.Fire

class CheckFireExtinguishedEvent(val npc: NPC) : Event {
    override fun execute() {
        EndConversationEvent(npc).execute()

        val listOfProperties:List<ObjectProperty> = npc.defaultLocation!!.gameObjects.List.flatMap { it.properties.List }
        val fire = listOfProperties.find { it is Fire } as Fire?
        /*if(fire!= null){
            StartConversationEvent("firenotfixed",npc).execute()
        }else{
            ClearQuestEvent(QuestIdentifier.FIRE).execute()
            StartConversationEvent("firefixed",npc).execute()
        }*/
    }

}
