package com.mygdx.game.Collitions

import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.Interfaces.Collition
import com.mygdx.game.Interfaces.MoveCollition

class FireCollition(val collitionOfAttachedObject: Collition, collitionWhileOnFire: MoveCollition): MoveCollition by collitionWhileOnFire{

    override fun filterCollitions(gameObjects: List<GameObject>): List<GameObject> {
        return gameObjects.filter { it.collition != collitionOfAttachedObject}
    }

}
