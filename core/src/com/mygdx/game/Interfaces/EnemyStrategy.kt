package com.mygdx.game.Interfaces

import com.badlogic.gdx.math.Circle
import com.mygdx.game.AbstractClasses.AggroStrategy
import com.mygdx.game.AbstractClasses.Enemy
import com.mygdx.game.AbstractClasses.GameObject

interface EnemyStrategy{
    val actionList:List<EnemyAction>
    fun getActions(enemy: Enemy): List<EnemyAction>
}
