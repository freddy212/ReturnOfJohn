package com.mygdx.game.Managers

import com.mygdx.game.Interfaces.Event

class EventManager {
    companion object {
        val eventManager = ResourceList<Event>()

        fun executeEvents(){
            for(event in eventManager.List){
                event.execute()
            }
        }
    }
}
