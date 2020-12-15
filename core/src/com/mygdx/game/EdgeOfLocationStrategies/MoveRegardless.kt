package com.mygdx.game.EdgeOfLocationStrategies

import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.MoveableObject
import com.mygdx.game.GetNextStep
import com.mygdx.game.Interfaces.CannotMoveStrategy
import com.mygdx.game.plus

class MoveRegardless: CannotMoveStrategy {
    override fun CannotMoveAction(moveableObject: MoveableObject) {
        val nextPos = GetNextStep(moveableObject.direction,moveableObject.speed)
        moveableObject.setPosition(Vector2(moveableObject.sprite.x,moveableObject.sprite.y) + nextPos,moveableObject)
    }
}