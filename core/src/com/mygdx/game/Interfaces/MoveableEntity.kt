package com.mygdx.game.Interfaces

import com.mygdx.game.Enums.Direction

interface MoveableEntity {
    fun move(d : Direction)
    var speed : Float
    var direction: Direction
    val movementStrategy: MovementStrategy
}