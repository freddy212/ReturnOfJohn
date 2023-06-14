package com.mygdx.game.AI.EnemyActions

import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.Enemy
import com.mygdx.game.Enums.Direction
import com.mygdx.game.Enums.getDirectionUnitVector
import com.mygdx.game.Interfaces.EnemyAction

class MoveDirection(val enemy:Enemy, val direction: Vector2): EnemyAction() {

    override fun executeEnemyAction() {
        enemy.move(direction)
    }

    override val probability = 1.0
}