package com.mygdx.game.Interfaces

import com.mygdx.game.AbstractClasses.MoveableObject
import com.mygdx.game.Enums.Direction

interface MovementStrategy {
    fun moveEntity(d: Direction, moveableObject: MoveableObject)
}