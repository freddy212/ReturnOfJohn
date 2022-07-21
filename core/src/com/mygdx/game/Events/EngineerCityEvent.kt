package com.mygdx.game.Events

import com.mygdx.game.GameObjects.MoveableEntities.Characters.NPC
import com.mygdx.game.Interfaces.AreaIdentifier
import com.mygdx.game.Interfaces.Event
import com.mygdx.game.Managers.AreaManager
import com.mygdx.game.Managers.LocationManager
import com.mygdx.game.Managers.SignalManager
import com.mygdx.game.Signal.SIGNALTYPE
import com.mygdx.game.Signal.Signal

class EngineerCityEvent(val npc: NPC): Event {
    override fun execute() {
        EndConversationEvent(npc).execute()
        val location = LocationManager.findLocation("location1",AreaIdentifier.MAINAREA)
        SignalManager.emitSignal(Signal(SIGNALTYPE.CHANGE_OBJECT_LOCATION,npc.entityId,0,0f,0f,"location1",AreaIdentifier.MAINAREA.ordinal))
        SignalManager.emitSignal(Signal(SIGNALTYPE.MOVE_OBJECT_COORDINATES,npc.entityId,0,location.topright.x - 400f,location.topright.y - 200f))
        SignalManager.emitSignal(Signal(SIGNALTYPE.ADD_OBJECT,0,0,0f,0f,"location1",AreaIdentifier.MAINAREA.ordinal))
        SignalManager.emitSignal(Signal(SIGNALTYPE.CHANGE_CONVERSATION,npc.entityId,0,0f,0f,"engineer2",AreaIdentifier.MAINAREA.ordinal))
    }
}