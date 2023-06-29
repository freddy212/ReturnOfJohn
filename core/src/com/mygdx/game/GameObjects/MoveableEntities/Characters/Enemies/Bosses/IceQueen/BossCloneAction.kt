package com.mygdx.game.GameObjects.MoveableEntities.Characters.Enemies.Bosses.boss

import com.mygdx.game.GameObjects.MoveableEntities.Characters.Enemies.Bosses.Boss
import com.mygdx.game.GameObjects.MoveableEntities.Characters.Enemies.Bosses.IceQueen.BossClone
import com.mygdx.game.Interfaces.EnemyAction

class BossCloneAction(val boss: Boss, val bossClone: BossClone, val actions: List<EnemyAction>, val availableFromStart: Boolean = false): EnemyAction() {
        override fun executeEnemyAction() {
            bossClone.setAggroed()
            bossClone.setPosition(boss.currentPosition())
            bossClone.setBossCloneActions(actions)
            bossClone.health = boss.health
            bossClone.addToLocation(boss.defaultLocation!!)
        }

        override val probability = 0.03

    override fun condition(): Boolean {
        val bossClone = boss.defaultLocation?.gameObjects?.List?.firstOrNull {it is BossClone}
        if(!availableFromStart){
            return bossClone == null && boss.health * 2 <= boss.maxHealth
        }
        return bossClone == null
    }

    override fun cleanUp() {
        val bossClone = boss.defaultLocation?.gameObjects?.List?.firstOrNull {it is BossClone}
        bossClone?.removeFromLocation()
        super.cleanUp()
    }
}