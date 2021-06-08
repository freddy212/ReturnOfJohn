package com.mygdx.game.Interfaces

import com.mygdx.game.AbstractClasses.DefaultCharacter
import com.mygdx.game.AbstractClasses.Enemy

interface EnemyAction {
    fun executeEnemyAction(enemy: Enemy)
    val probability: Double
    fun condition(enemy: Enemy): Boolean = true
}
