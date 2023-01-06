package com.mygdx.game.Events

import com.mygdx.game.Interfaces.Button
import com.mygdx.game.Interfaces.Event
import com.mygdx.game.Managers.SignalManager
import com.mygdx.game.Signal.Signals.ButtonAcceptedSignal

class ButtonEvent(val allActivatedEvent: Event, val emitWhenActivated: Boolean = false): Event {
    val doorButtons = mutableListOf<Button>()
    fun addButton(button: Button){
        doorButtons.add(button)
    }
    override fun execute() {
        if(isAllButtonsActivated()){
            allActivatedEvent.execute();
            if(emitWhenActivated){
                doorButtons.forEach {
                    SignalManager.emitSignal(ButtonAcceptedSignal(it.entityId))
                }
            }
        }
    }

    fun isAllButtonsActivated(): Boolean {
        return doorButtons.all { it.activated }
    }
}