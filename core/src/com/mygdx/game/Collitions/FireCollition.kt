package com.mygdx.game.Collitions

import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.Interfaces.Collition
import com.mygdx.game.Interfaces.MoveCollition

class FireCollition(val collitionOfAttachedObject: Collition, val collitionWhileOnFire: MoveCollition): MoveCollition by CanMoveCollition{
    override fun collitionHappened(collidedObject: GameObject) {
        collitionWhileOnFire.collitionHappened(collidedObject)
    }

    override fun filterCollitions(gameObjects: List<GameObject>): List<GameObject> {
        return gameObjects.filter { it.collition != collitionOfAttachedObject}
    }

}
