package com.mygdx.game.AI.EnemyActions

import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.Enemy
import com.mygdx.game.Interfaces.EnemyAction
import com.mygdx.game.getOppositeUnitVector
import com.mygdx.game.getUnitVectorTowardsPoint
import com.mygdx.game.player

class ChangeDirection:EnemyAction {
    override fun executeEnemyAction(enemy: Enemy) {
        enemy.setCharacterRotation(getOppositeUnitVector(Vector2( player.sprite.x, player.sprite.y),Vector2(enemy.sprite.x,enemy.sprite.y)))
    }

    override val probability = 0.5
}