package com.mygdx.game.Events

import com.mygdx.game.AbstractClasses.DefaultCharacter
import com.mygdx.game.Interfaces.Event
import com.mygdx.game.Managers.EventManager

class ImmuneEvent(val immunityFrames: Float, val character: DefaultCharacter): Event {
        var counter = 0
        override fun execute() {
            counter++
            if (counter > immunityFrames) {
                if(EventManager.eventManager.List.filterIsInstance<ImmuneEvent>().size == 1){
                    character.immuneToDamage = false
                }
                EventManager.eventManager.remove(this)
            }
        }
}