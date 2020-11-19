package com.mygdx.game.Collitions

import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.Interfaces.MoveCollition
import com.mygdx.game.Interfaces.DynamicEntity

object IllegalMoveCollition: MoveCollition {
    override fun collitionHappened(entity: DynamicEntity, collidedObject: GameObject) {

    }

    override var canMoveAfterCollition = false

}