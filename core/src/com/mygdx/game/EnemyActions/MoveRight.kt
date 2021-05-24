package com.mygdx.game.EnemyActions

import com.mygdx.game.AbstractClasses.DefaultCharacter
import com.mygdx.game.Enums.Direction
import com.mygdx.game.Enums.getDirectionUnitVector
import com.mygdx.game.Interfaces.EnemyAction

class MoveRight: EnemyAction {

    override fun executeEnemyAction(enemy: DefaultCharacter) {
        enemy.move(getDirectionUnitVector(Direction.RIGHT))
    }

    override val probability = 0.5
}