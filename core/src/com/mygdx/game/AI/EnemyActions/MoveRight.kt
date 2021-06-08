package com.mygdx.game.AI.EnemyActions

import com.mygdx.game.AbstractClasses.Enemy
import com.mygdx.game.Enums.Direction
import com.mygdx.game.Enums.getDirectionUnitVector
import com.mygdx.game.Interfaces.EnemyAction

class MoveRight: EnemyAction {

    override fun executeEnemyAction(enemy: Enemy) {
        enemy.move(getDirectionUnitVector(Direction.RIGHT))
    }

    override val probability = 0.3
}