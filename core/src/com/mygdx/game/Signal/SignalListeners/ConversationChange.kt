package com.mygdx.game.Signal.SignalListeners

import com.mygdx.game.GameObjects.MoveableEntities.Characters.NPC
import com.mygdx.game.Interfaces.AreaIdentifier
import com.mygdx.game.Managers.LocationManager
import com.mygdx.game.Signal.SIGNALTYPE
import com.mygdx.game.Signal.Signal
import com.mygdx.game.Signal.SignaledEventListener
import com.mygdx.game.getGameObjectWithEntityId

class ConversationChange: SignaledEventListener {
    override val signaltype = SIGNALTYPE.CHANGE_CONVERSATION

    override fun triggerEvent(signal: Signal) {
        val gameObject =  getGameObjectWithEntityId(signal.id) as NPC
        gameObject.conversationsHandler.setConversation(signal.stringValue)
    }
}