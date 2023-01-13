package com.mygdx.game.Collitions

import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.Interfaces.MoveCollition

object CanMoveCollition: MoveCollition{
    override fun collitionHappened(collidedObject: GameObject) {

    }

    override var canMoveAfterCollition = true

}