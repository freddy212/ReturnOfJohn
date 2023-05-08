package com.mygdx.game.GameObjects.MoveableEntities.Characters.Enemies.Bosses.IceQueen

import com.mygdx.game.GameObjects.Terrain.TeleportPad
import com.mygdx.game.Interfaces.EnemyAction

class IceQueenCloneAction(val iceQueen: IceQueen,val teleportPoints: List<TeleportPad>): EnemyAction() {
    override fun executeEnemyAction() {
        val iceQueenClone = IceQueenClone(iceQueen.currentPosition(), iceQueen.size, iceQueen.defaultLocation, iceQueen, teleportPoints)
        iceQueenClone.addToLocation(iceQueenClone.defaultLocation!!)
    }

    override val probability = 0.03

    override fun condition(): Boolean {
        val iceQueenClone = iceQueen.defaultLocation?.gameObjects?.List?.firstOrNull {it is IceQueenClone}
        return iceQueenClone == null && iceQueen.health * 2 <= iceQueen.maxHealth
    }

    override fun cleanUp() {
        val iceQueenClone = iceQueen.defaultLocation?.gameObjects?.List?.firstOrNull {it is IceQueenClone}
        iceQueenClone?.removeFromLocation()
        super.cleanUp()
    }
}