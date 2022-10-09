package com.mygdx.game.Events

import com.mygdx.game.GameObjects.MoveableEntities.Characters.NPC
import com.mygdx.game.Interfaces.AreaIdentifier
import com.mygdx.game.Interfaces.Event
import com.mygdx.game.Managers.LocationManager
import com.mygdx.game.Managers.SignalManager
import com.mygdx.game.Signal.SignalListeners.ADDMETHODS
import com.mygdx.game.Signal.Signals.AddObjectSignal
import com.mygdx.game.Signal.Signals.ChangeConversationSignal
import com.mygdx.game.Signal.Signals.ChangeObjectLocationSignal
import com.mygdx.game.Signal.Signals.MoveObjectSignal

class EngineerCityEvent(val npc: NPC): Event {
    override fun execute() {
        EndConversationEvent(npc).execute()
        val location = LocationManager.findLocation("location1",AreaIdentifier.MAINAREA)
        SignalManager.emitSignal(ChangeObjectLocationSignal(npc.entityId,"location1",AreaIdentifier.MAINAREA))
        SignalManager.emitSignal(MoveObjectSignal(npc.entityId,location.topright.x - 400f,location.topright.y - 200f))
        SignalManager.emitSignal(AddObjectSignal(ADDMETHODS.ENGINEER,"location1",AreaIdentifier.MAINAREA))
        SignalManager.emitSignal(ChangeConversationSignal(npc.entityId,"engineer2"))
    }
}