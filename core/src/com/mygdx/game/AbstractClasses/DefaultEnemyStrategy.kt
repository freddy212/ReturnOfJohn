package com.mygdx.game.AbstractClasses

import com.badlogic.gdx.math.Circle
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.Interfaces.EnemyStrategy
import com.mygdx.game.Interfaces.EnemyAction
import com.mygdx.game.Interfaces.Event
import com.mygdx.game.Managers.EventManager
import com.mygdx.game.player
import kotlin.random.Random

interface ShouldBeAggroedStrategy {
    fun ShouldBeAggroed(): Boolean
}

class InsideCircle(val circle: Circle, val gameObject: GameObject = player) : ShouldBeAggroedStrategy {
    override fun ShouldBeAggroed(): Boolean {
        return circle.contains(Vector2(gameObject.sprite.x, gameObject.sprite.y))
    }
}

interface AggroableEntity {
    fun isAggroed(): Boolean
    fun setAggroed()
    fun resetAggro()

    var aggroed: Boolean
}

open class DefaultAggroableEntity() : AggroableEntity {
    override var aggroed = false
    override fun resetAggro() {
        aggroed = false
    }

    override fun setAggroed() {
        aggroed = true
    }

    override fun isAggroed(): Boolean {
        return aggroed
    }
}

open class DefaultEnemyStrategy(override val actionList: List<EnemyAction>) : EnemyStrategy {

    var lockedAction: EnemyAction? = null

    fun lockAction(action: EnemyAction) {
        lockedAction = action
        EventManager.eventManager.add(object : Event {
            var counter = 0
            override fun execute() {
                counter++
                if (counter > action.framesToBlock) {
                    lockedAction = null
                    action.active = false
                    action.cleanUp()
                    EventManager.eventManager.remove(this)
                }
            }

        })
    }

    override fun getActions(enemy: Enemy): List<EnemyAction> {
        var actions = mutableListOf<EnemyAction>()
        val random = Random.nextDouble(0.0, 1.0)
        val randomlySelectedActions = actionList.filter { it.probability >= random }

        for (action in randomlySelectedActions.shuffled()) {
            if (action.condition() && (lockedAction == null || action == lockedAction)) {
                if (action.shouldBlock && lockedAction == null) {
                    action.active = true
                    lockAction(action)
                }
                actions.add(action)
            }
        }
        return actions
    }

}
