package com.mygdx.game.Events

import com.mygdx.game.Interfaces.Event
import com.mygdx.game.Managers.EventManager

class AddEventEvent(val event: Event):Event {
    override fun execute() {
        EventManager.eventManager.add(event)
    }

}