package com.mygdx.game.Events

import com.mygdx.game.Interfaces.Event

class CompositeEvent(val events: List<Event>) : Event {
    override fun execute() {
        events.forEach { it.execute() }
    }

}
