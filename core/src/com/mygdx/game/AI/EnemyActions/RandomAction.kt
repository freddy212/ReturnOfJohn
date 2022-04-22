package com.mygdx.game.AI.EnemyActions

import com.mygdx.game.AbstractClasses.Enemy
import com.mygdx.game.Interfaces.EnemyAction
import com.mygdx.game.Interfaces.Timer
import kotlin.random.Random

class RandomAction(val actions: List<EnemyAction>, val timer: Timer): EnemyAction() {
    private var primaryAction = actions[0]
    override fun executeEnemyAction(enemy: Enemy) {
        primaryAction.executeEnemyAction(enemy)
    }


    override val probability: Double
        get() = primaryAction.probability

    override fun condition(enemy: Enemy): Boolean {
        if(timer.tryUseCooldown()){
            val index = Random.nextInt(actions.size)
            primaryAction = actions[index]
        }
        return primaryAction.condition(enemy)
    }
}