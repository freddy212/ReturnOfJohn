package com.mygdx.game.Collitions

import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.Interfaces.EveryFrameCollition
import com.mygdx.game.Interfaces.MoveCollition
import com.mygdx.game.Locations.DefaultLocation

object RemoveDotDamageCollition: EveryFrameCollition{
    override fun collitionHappened(entity: GameObject, collidedObject: GameObject) {

    }

    override fun filterCollitions(gameObjects: List<GameObject>): List<GameObject> {
        return gameObjects.filter { it !is DefaultLocation }
    }
}