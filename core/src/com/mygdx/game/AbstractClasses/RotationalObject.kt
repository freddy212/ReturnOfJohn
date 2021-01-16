package com.mygdx.game.AbstractClasses

import com.badlogic.gdx.math.Vector2

interface RotationalObject {
    var angle: Float
    fun setRotation(unitVectorDirection: Vector2, gameObject: GameObject,angleModifier: Float)
}