package com.mygdx.game.AI.EnemyActions

import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.Enemy
import com.mygdx.game.Interfaces.EnemyAction
import com.mygdx.game.distance
import com.mygdx.game.getUnitVectorTowardsPoint
import com.mygdx.game.player

class EnemyMoveTowardsPoint(val enemy: Enemy, val point: Vector2): EnemyAction() {
    val distanceToStop = 2
    override fun executeEnemyAction() {
        enemy.move(getUnitVectorTowardsPoint(Vector2(enemy.sprite.x,enemy.sprite.y), point))
    }

    override val probability = 1.0
    override fun condition(): Boolean {
        val distanceBetween =  distance(point, Vector2(enemy.sprite.x,enemy.sprite.y))
        return  distanceBetween >= distanceToStop
    }

}