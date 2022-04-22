package com.mygdx.game.Interfaces

import com.mygdx.game.AbstractClasses.DefaultCharacter
import com.mygdx.game.AbstractClasses.Enemy
import com.mygdx.game.Managers.EventManager



abstract class EnemyAction {
    abstract fun executeEnemyAction(enemy: Enemy)
    abstract val probability: Double
    open fun condition(enemy: Enemy): Boolean = true
    var blocking: Boolean = false
    open val shouldBlock = false
    open val framesToBlock = 150
    open var active = false
    open fun cleanUp() {}
}
