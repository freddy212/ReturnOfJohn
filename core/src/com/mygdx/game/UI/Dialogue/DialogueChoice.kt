package com.mygdx.game.UI.Dialogue

import com.mygdx.game.Events.ContinueConversationEvent
import com.mygdx.game.GameObjects.MoveableEntities.Characters.NPC
import com.mygdx.game.Interfaces.Event

class DialogueChoice(val Option: String, npc: NPC, var event:Event = ContinueConversationEvent(npc)) {

}
