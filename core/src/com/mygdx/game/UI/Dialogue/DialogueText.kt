package com.mygdx.game.UI.Dialogue

import com.mygdx.game.Events.ContinueConversationEvent
import com.mygdx.game.GameObjects.MoveableEntities.Characters.NPC
import com.mygdx.game.Interfaces.Event

open class DialogueText(val text: String, npc: NPC, open var event:Event = ContinueConversationEvent(npc)) {

}