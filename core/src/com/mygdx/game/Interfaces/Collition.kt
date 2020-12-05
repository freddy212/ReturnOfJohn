package com.mygdx.game.Interfaces

import com.mygdx.game.AbstractClasses.GameObject

interface Collition {
    fun collitionHappened(entity: GameObject, collidedObject: GameObject)
}