package com.mygdx.game.Interfaces

import com.mygdx.game.AbstractClasses.Enemy

interface EnemyStrategy{
    val actionList:List<EnemyAction>
    fun getActions(enemy: Enemy): List<EnemyAction>
}
