package com.mygdx.game.Collitions

import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.Interfaces.MoveCollition
import com.mygdx.game.LocationImpl

object RemoveDotDamageCollition: MoveCollition by CanMoveCollition {
    override fun filterCollitions(gameObjects: List<GameObject>): List<GameObject> {
        return gameObjects.filter { it !is LocationImpl }
    }
}