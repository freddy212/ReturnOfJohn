package com.mygdx.game.Collitions

import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.Interfaces.MoveCollition

class ToggleCollition(val collitionBefore: MoveCollition, val collitionAfter: MoveCollition): MoveCollition {
    private var conditionFulfilled = false

    fun conditionFulfilled(){
        conditionFulfilled = true
    }
    val collition:MoveCollition
            get() = if(!conditionFulfilled) collitionBefore else collitionAfter
    override var canMoveAfterCollition: Boolean = collitionBefore.canMoveAfterCollition
        get() = collition.canMoveAfterCollition
    override fun collitionHappened(collidedObject: GameObject) {
        collition.collitionHappened(collidedObject)
    }
}