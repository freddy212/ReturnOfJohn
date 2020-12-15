package com.mygdx.game.EdgeOfLocationStrategies

import com.mygdx.game.AbstractClasses.MoveableObject
import com.mygdx.game.Interfaces.CannotMoveStrategy

class RemoveObject: CannotMoveStrategy {
    override fun CannotMoveAction(moveableObject: MoveableObject) {
        moveableObject.location!!.removeGameObject(moveableObject)
    }
}