package com.mygdx.game.AbstractClasses

import com.badlogic.gdx.math.Circle
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.Interfaces.EnemyStrategy
import com.mygdx.game.Interfaces.EnemyAction
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
class DefaultAggro(): AggroStrategy{
    override fun isAggroed(): Boolean {
        return true
    }
}

open class DefaultEnemyStrategy(override val actionList : List<EnemyAction>) : EnemyStrategy {

    override fun getActions(enemy: Enemy): List<EnemyAction>{
        val validActions = actionList.filter {it.condition(enemy)}
        val random = Random.nextDouble(0.0,1.0)
        var current = 0.0
        var actions = mutableListOf<EnemyAction>()

        for(action in validActions){
            current = action.probability
            if(current >= random){
                actions.add(action)
            }
        }
        return actions
    }

}
