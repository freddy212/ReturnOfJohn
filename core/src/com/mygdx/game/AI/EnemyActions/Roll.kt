package com.mygdx.game.AI.EnemyActions

import com.mygdx.game.DefaultTextureHandler
import com.mygdx.game.GameObjects.MoveableEntities.Characters.Enemies.Bosses.RockBoss.RockBoss
import com.mygdx.game.Interfaces.EnemyAction
class Roll(val enemyMoveBasedOnPlayer: MoveBasedOnPlayer, val rockBoss: RockBoss): EnemyAction() {

    private var counter = 0
    val originalTexture = rockBoss.sprite.texture

    val initFramesToBlock = 90

    override fun executeEnemyAction() {
        if(counter == 0){
            rockBoss.sprite.texture = DefaultTextureHandler.getTexture("RockManCrouched.png")
            rockBoss.setCurrentSpeed(rockBoss.getCurrentSpeed() * 1.25f)
            rockBoss.isRolling = true
        }
        enemyMoveBasedOnPlayer.executeEnemyAction()
        counter++
        if(!rockBoss.isRolling && framesToBlock == initFramesToBlock){
            framesToBlock = counter
        }
    }

    override val probability: Double
        get() = if(active) 1.0 else 0.005

    override val shouldBlock = true
    override var framesToBlock = initFramesToBlock

    override fun cleanUp() {
        rockBoss.sprite.texture = originalTexture
        rockBoss.setCurrentSpeed(rockBoss.baseSpeed)
        counter = 0
        rockBoss.isRolling = false
        framesToBlock = initFramesToBlock
    }

}