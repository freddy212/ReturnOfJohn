package com.mygdx.game.AI.EnemyActions

import com.mygdx.game.AbstractClasses.Enemy
import com.mygdx.game.Interfaces.EnemyAction
import com.mygdx.game.Interfaces.Timer
import kotlin.random.Random

class RandomAction(val actions: List<EnemyAction>, val timer: Timer, val enemy:Enemy, val canRepeat: Boolean = true): EnemyAction() {
    private var primaryAction = actions[0]
    override fun executeEnemyAction() {
        primaryAction.executeEnemyAction()
    }


    override val probability: Double
        get() = primaryAction.probability

    override fun condition(): Boolean {
        val oldPrimaryAction = primaryAction
        if(timer.tryUseCooldown()){
            primaryAction = getRandomAction()
            if(!canRepeat && oldPrimaryAction == primaryAction){
                while (oldPrimaryAction == primaryAction){
                    primaryAction = getRandomAction()
                }
            }
        }
        return primaryAction.condition()
    }

    // List must be larger than one element, or there will be an endless loop when choosing new random action.
    fun getRandomAction(): EnemyAction{
        val index = Random.nextInt(actions.size)
        return actions[index]
    }
}