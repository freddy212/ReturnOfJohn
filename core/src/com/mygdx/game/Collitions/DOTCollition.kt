package com.mygdx.game.Collitions

import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.GameObjects.MoveableEntities.Characters.Player
import com.mygdx.game.Interfaces.AreaEntranceCollition
import com.mygdx.game.Interfaces.DefaultAreaEntranceCollition
import com.mygdx.game.Interfaces.Event
import com.mygdx.game.Managers.EventManager
import com.mygdx.game.player

class InsideAreaEvent() : Event {
    override fun execute() {
        player.loseHealth(1f, false)
    }

}

class DOTCollition : DefaultAreaEntranceCollition() {

    val insideAreaEvent = InsideAreaEvent()

    override fun movedInsideAction(objectEntered: GameObject) {
        EventManager.eventManager.add(insideAreaEvent)
    }

    override fun movedOutsideAction(objectLeaved: GameObject) {
        EventManager.eventManager.remove(insideAreaEvent)
    }

    override fun collitionHappened(collidedObject: GameObject) {
        if (collidedObject is Player) {
            super.collitionHappened(collidedObject)
        }
    }

    override fun filterCollitions(gameObjects: List<GameObject>): List<GameObject> {
        val dotCollitionObjects = gameObjects.filter { it.collition is DOTCollition}
        return gameObjects.minus(dotCollitionObjects.toSet()) + dotCollitionObjects.take(1)
    }
}
