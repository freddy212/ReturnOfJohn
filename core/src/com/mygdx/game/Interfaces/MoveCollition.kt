package com.mygdx.game.Interfaces

import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.GameObject

interface MoveCollition: Collition {
    var canMoveAfterCollition: Boolean
}