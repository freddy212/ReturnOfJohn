package com.mygdx.game.AbstractClasses

import com.badlogic.gdx.math.Vector2
import com.mygdx.game.crossLocationGameObjects

abstract class ItemAbility(Position: Vector2, size: Vector2) : GameObject(Position, size) {
    abstract val triggerKey: Int
    open fun activeAction(){
        crossLocationGameObjects.add(this)
    }
    open fun InactiveAction(){
        crossLocationGameObjects.remove(this)
    }
}