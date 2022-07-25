package com.mygdx.game.Collitions

import com.mygdx.game.GameObjects.DoorButton
import com.mygdx.game.Interfaces.Event

class DoorButtonEvent(val allAcceptedEvent: Event): Event {
    val doorButtons = mutableListOf<DoorButton>()
    fun addButton(button: DoorButton){
        doorButtons.add(button)
    }
    override fun execute() {
        if(doorButtons.all { it.activated }){
            allAcceptedEvent.execute();
        }
    }
}