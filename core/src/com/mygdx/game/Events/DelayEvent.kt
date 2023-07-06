package com.mygdx.game.Events

import com.mygdx.game.Interfaces.Event
import com.mygdx.game.Managers.EventManager

class DelayEvent(val event: Event,val frames: Int): Event {

    var counter = 0
    override fun execute() {
        if(counter == frames){
            event.execute()
            EventManager.eventManager.remove(this)
        }
        counter += 1
    }
}