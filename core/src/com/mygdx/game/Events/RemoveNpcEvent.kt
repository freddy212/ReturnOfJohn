package com.mygdx.game.Events

import com.mygdx.game.GameObjects.MoveableEntities.NPC
import com.mygdx.game.Interfaces.Event

class RemoveNpcEvent(val npc: NPC):Event {
    override fun execute() {
        npc.removeFromLocation()
    }

}
