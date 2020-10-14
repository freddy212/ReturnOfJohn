package com.mygdx.game.Collitions

import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.Interfaces.Collition
import com.mygdx.game.Interfaces.MoveableEntity

object AbyssCollition: Collition by CanMoveCollition {
    override fun collitionHappened(entity: MoveableEntity, collitionPosition: Vector2, collidedObject: GameObject) {
        println("you fell into the abyss")
    }
}