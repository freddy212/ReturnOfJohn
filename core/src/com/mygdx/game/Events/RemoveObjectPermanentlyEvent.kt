package com.mygdx.game.Events

import com.mygdx.game.Interfaces.Event
import com.mygdx.game.Managers.SignalManager
import com.mygdx.game.Saving.SaveStateEntity
import com.mygdx.game.Signal.Signals.RemoveObjectSignal

class RemoveObjectPermanentlyEvent(val entity: SaveStateEntity): Event {
    override fun execute() {
        SignalManager.emitSignal(RemoveObjectSignal(entity.entityId))
    }
}