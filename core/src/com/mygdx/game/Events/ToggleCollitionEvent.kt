package com.mygdx.game.Events

import com.mygdx.game.Collitions.ToggleCollition
import com.mygdx.game.Interfaces.Event
import com.mygdx.game.ObjectProperties.Fire

class ToggleCollitionEvent(val toggleCollition: ToggleCollition): Event {
    override fun execute() {
        toggleCollition.conditionFulfilled()
    }

}
