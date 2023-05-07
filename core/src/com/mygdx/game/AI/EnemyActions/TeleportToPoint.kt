package com.mygdx.game.AI.EnemyActions

import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.Enemy
import com.mygdx.game.GameObjects.Terrain.TeleportPad
import com.mygdx.game.GetCollidingObjects
import com.mygdx.game.Interfaces.EnemyAction
import com.mygdx.game.Managers.LocationManager
import com.mygdx.game.distance
import com.mygdx.game.getUnitVectorTowardsPoint

class TeleportToPoint(val enemy: Enemy, val point: Vector2): EnemyAction() {

    private var counter = 0
    override fun executeEnemyAction() {
        if(counter == 15){
            enemy.setPosition(point)
        }
        counter ++
    }

    override val probability: Double
        get() = if(active) 1.0 else 0.02

    override val shouldBlock = true
    override val framesToBlock = 45

    override fun condition(): Boolean {
        val collidingObjects = GetCollidingObjects(enemy,enemy.polygon,LocationManager.MoveCollitionGameObjects)
        val teleportPadCollitions = collidingObjects.filter { it is TeleportPad }
        return teleportPadCollitions.isNotEmpty()
    }

    override fun cleanUp() {
        counter = 0
    }


}