package com.mygdx.game.Collitions

import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.Interfaces.MoveCollition

object IllegalMoveCollition: MoveCollition {
    override fun collitionHappened(collidedObject: GameObject) {

    }
    override val canMoveAfterCollition = false
}