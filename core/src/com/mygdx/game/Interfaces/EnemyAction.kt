package com.mygdx.game.Interfaces

import com.mygdx.game.AbstractClasses.DefaultCharacter

interface EnemyAction {
    fun executeEnemyAction(character: DefaultCharacter)
    val probability: Double
    fun condition(): Boolean = true
}
