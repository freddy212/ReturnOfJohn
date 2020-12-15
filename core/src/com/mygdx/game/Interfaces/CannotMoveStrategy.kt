package com.mygdx.game.Interfaces

import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.AbstractClasses.MoveableObject

interface CannotMoveStrategy {
    fun CannotMoveAction(moveableObject: MoveableObject)
}