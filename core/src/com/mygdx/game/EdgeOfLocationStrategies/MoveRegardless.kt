package com.mygdx.game.EdgeOfLocationStrategies

import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.MoveableObject
import com.mygdx.game.Interfaces.CannotMoveStrategy
import com.mygdx.game.plus
import com.mygdx.game.times

class MoveRegardless: CannotMoveStrategy {
    override fun CannotMoveAction(moveableObject: MoveableObject) {
        moveableObject.setPosition(Vector2(moveableObject.sprite.x,moveableObject.sprite.y) + moveableObject.unitVectorDirection * moveableObject.currentSpeed)
    }
}