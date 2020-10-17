package com.mygdx.game.Interfaces

import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.AbstractClasses.MoveableObject

interface EdgeOfLocationStrategy {
    fun handleEdgeOfLocation(moveableObject: MoveableObject)
}