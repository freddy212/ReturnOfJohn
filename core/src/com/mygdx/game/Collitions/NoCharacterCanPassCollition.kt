package com.mygdx.game.Collitions

import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.Interfaces.Character
import com.mygdx.game.Interfaces.MoveCollition

class NoCharacterCanPassCollition:MoveCollition {
    override var canMoveAfterCollition = true
    override fun collitionHappened(entity: GameObject, collidedObject: GameObject) {
        canMoveAfterCollition = !(entity is Character || collidedObject is Character)
    }

}
