package com.mygdx.game.Events

import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.GameObjects.MoveableEntities.NPC
import com.mygdx.game.Interfaces.Event

class RemoveGameObjectEvent(val gameObject: GameObject):Event {
    override fun execute() {
        gameObject.removeFromLocation()
    }

}
