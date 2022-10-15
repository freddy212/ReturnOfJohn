package com.mygdx.game.Managers

import com.mygdx.game.Interfaces.Event
import com.mygdx.game.Utils.ResourceList

class EventManager {
    companion object {
        val eventManager = ResourceList<Event>()

        fun executeEvents(){
            for(event in eventManager.List){
                event.execute()
                if(event.runOnce()){
                    eventManager.remove(event)
                }
            }
        }
    }
}
