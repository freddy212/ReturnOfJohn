package com.mygdx.game.UI.Dialogue

import com.mygdx.game.Events.ContinueConversationEvent
import com.mygdx.game.Events.DefaultEvent
import com.mygdx.game.GameObjects.MoveableEntities.NPC
import com.mygdx.game.Interfaces.Event

class DialogueChoice(val Option: String, npc: NPC,open var event:Event = ContinueConversationEvent(npc)) {

}
