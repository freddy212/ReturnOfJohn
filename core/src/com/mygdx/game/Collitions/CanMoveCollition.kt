package com.mygdx.game.Collitions

import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.Interfaces.BaseCollition
import com.mygdx.game.Interfaces.MoveCollition
import com.mygdx.game.Interfaces.DynamicEntity

object CanMoveCollition: MoveCollition{
    override fun collitionHappened(collidedObject: GameObject) {

    }

    override var canMoveAfterCollition = true

}