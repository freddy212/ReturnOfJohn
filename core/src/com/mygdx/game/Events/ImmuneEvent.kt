package com.mygdx.game.Events

import com.mygdx.game.AbstractClasses.DefaultCharacter
import com.mygdx.game.Interfaces.Event
import com.mygdx.game.Managers.EventManager

class ImmuneEvent(val immunityFrames: Float, val character: DefaultCharacter): Event {
        var counter = 0
        override fun execute() {
            counter++
            if (counter > immunityFrames) {
                val immuneEvents = EventManager.eventManager.List.filterIsInstance<ImmuneEvent>()
                if(immuneEvents.filter { it.character == character}.size == 1){
                    character.immuneToDamage = false
                }
                EventManager.eventManager.remove(this)
            }
        }
}