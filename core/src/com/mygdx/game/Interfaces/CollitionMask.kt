package com.mygdx.game.Interfaces

import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.GameObjects.MoveableEntities.Characters.Player
import java.util.function.Predicate

//Use collition mask to set what objects can collide with what other objects
interface CollitionMask {
    val canCollideWith: (GameObject) -> Boolean
}

class DefaultCollitionMask(override val canCollideWith: (GameObject) -> Boolean = { _:GameObject -> true }): CollitionMask {

}