package com.mygdx.game.AbstractClasses

import com.badlogic.gdx.math.Vector2
import com.mygdx.game.angleBetweenPoints
import com.mygdx.game.unitVectorToAngle

class DefaultRotationalObject: RotationalObject {
    override var angle = 0f
    override fun setRotation(unitVectorDirection: Vector2, gameObject: GameObject, angleModifier: Float) {

            angle = unitVectorToAngle(unitVectorDirection) + angleModifier
            gameObject.polygon.rotation = angle
            gameObject.sprite.rotation = angle
    }
}