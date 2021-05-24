package com.mygdx.game.Interfaces

import com.badlogic.gdx.math.Circle
import com.mygdx.game.AbstractClasses.AggroStrategy
import com.mygdx.game.AbstractClasses.GameObject

interface EnemyStrategy{
    fun aggroed(aggroStrategy: AggroStrategy): Boolean
    val actionList:List<EnemyAction>
    fun getNextAction(): EnemyAction
}
