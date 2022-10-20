package com.mygdx.game.AbstractClasses

import com.badlogic.gdx.math.Circle
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.Interfaces.EnemyStrategy
import com.mygdx.game.Interfaces.EnemyAction
import com.mygdx.game.Interfaces.Event
import com.mygdx.game.Managers.EventManager
import com.mygdx.game.player
import kotlin.random.Random

interface AggroStrategy{
    fun isAggroed(): Boolean
}
class InsideCircle(val circle: Circle): AggroStrategy{
    override fun isAggroed(): Boolean {
        return circle.contains(Vector2(player.sprite.x, player.sprite.y))
    }
}

open class DefaultEnemyStrategy(override val actionList : List<EnemyAction>) : EnemyStrategy {

    var lockedAction: EnemyAction? = null

    fun lockAction(action: EnemyAction){
        lockedAction = action
        EventManager.eventManager.add(object : Event {
            var counter = 0
            override fun execute() {
                counter++
                if(counter > action.framesToBlock){
                    lockedAction = null
                    action.active = false
                    action.cleanUp()
                    EventManager.eventManager.remove(this)
                }
            }

            override fun runOnce(): Boolean {
                return super.runOnce()
            }

        })
    }

    override fun getActions(enemy: Enemy): List<EnemyAction>{
        val validActions = actionList.filter {it.condition(enemy)}
        val random = Random.nextDouble(0.0,1.0)
        var current = 0.0
        var actions = mutableListOf<EnemyAction>()

            for (action in validActions.shuffled()) {
                current = action.probability

                if (current >= random && (lockedAction == null || action == lockedAction)) {
                    if(action.shouldBlock && lockedAction == null){
                        action.active = true
                        lockAction(action)
                    }
                    actions.add(action)
                }
            }
        return actions
    }

}
