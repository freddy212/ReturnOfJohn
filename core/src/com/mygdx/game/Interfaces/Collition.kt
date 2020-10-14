package com.mygdx.game.Interfaces

import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.GameObject

interface Collition {
    fun collitionHappened(entity: MoveableEntity, collitionPosition: Vector2, collidedObject: GameObject)
    var canMoveAfterCollition: Boolean
}