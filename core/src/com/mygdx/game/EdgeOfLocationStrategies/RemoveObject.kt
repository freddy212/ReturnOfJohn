package com.mygdx.game.EdgeOfLocationStrategies

import com.mygdx.game.AbstractClasses.MoveableObject
import com.mygdx.game.Interfaces.EdgeOfLocationStrategy

class RemoveObject: EdgeOfLocationStrategy {
    override fun handleEdgeOfLocation(moveableObject: MoveableObject) {
        moveableObject.location!!.removeGameObject(moveableObject)
    }
}