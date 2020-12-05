package com.mygdx.game.Interfaces

import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.AbstractClasses.MoveableObject
import com.mygdx.game.Enums.Direction
import java.util.*

interface MovementStrategy {
    fun moveEntity(direction: Direction, moveableObject: MoveableObject): Boolean
}