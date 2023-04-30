package com.mygdx.game.AI.EnemyActions

import com.mygdx.game.AbstractClasses.Enemy
import com.mygdx.game.DefaultTextureHandler
import com.mygdx.game.Interfaces.EnemyAction
class EnemyRoll(val enemyMoveBasedOnPlayer: EnemyMoveBasedOnPlayer, val enemy: Enemy): EnemyAction() {

    private var counter = 0
    val originalTexture = enemy.sprite.texture

    override fun executeEnemyAction() {
        if(counter == 0){
            enemy.sprite.texture = DefaultTextureHandler.getTexture("RockManCrouched.png")
            enemy.setCurrentSpeed(enemy.getCurrentSpeed() * 2.5f)
        }
        enemyMoveBasedOnPlayer.executeEnemyAction()
        counter++
    }

    override val probability: Double
        get() = if(active) 1.0 else 0.005

    override val shouldBlock = true
    override val framesToBlock = 90

    override fun cleanUp() {
        enemy.sprite.texture = originalTexture
        enemy.setCurrentSpeed(enemy.baseSpeed)
        counter = 0
    }

}