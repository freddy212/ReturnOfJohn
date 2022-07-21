package com.mygdx.game.Events

import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.Interfaces.Event
import com.mygdx.game.Managers.EventManager

class ActionAfterFramesEvent(val frames: Float, val action: () -> Unit): Event {
    private var counter = 0
    override fun execute() {
        counter++
        if (counter > frames) {
            action()
            EventManager.eventManager.remove(this)
        }
    }
}