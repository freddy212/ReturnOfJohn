package com.mygdx.game.EdgeOfLocationStrategies

import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.AbstractClasses.MoveableObject
import com.mygdx.game.Interfaces.EdgeOfLocationStrategy
import com.mygdx.game.Managers.MovableObjectManager

class RemoveObject: EdgeOfLocationStrategy {
    override fun handleEdgeOfLocation(moveableObject: MoveableObject) {
        MovableObjectManager.removeMoveableObject(moveableObject)
    }
}