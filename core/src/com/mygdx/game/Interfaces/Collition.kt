package com.mygdx.game.Interfaces

import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.MoveableEntity

interface Collition {
    fun collitionHappened(entity: MoveableEntity, collitionPosition: Vector2)
}