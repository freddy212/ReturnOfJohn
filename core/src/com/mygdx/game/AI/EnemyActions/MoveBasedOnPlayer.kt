package com.mygdx.game.AI.EnemyActions

import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.Enemy
import com.mygdx.game.Interfaces.EnemyAction
import com.mygdx.game.distance
import com.mygdx.game.player

class MoveBasedOnPlayer(private val distanceToStop: Float, private val directionFunction: (Vector2, Vector2) -> Vector2, val enemy:Enemy): EnemyAction() {
    override fun executeEnemyAction() {
        enemy.move(directionFunction(Vector2(enemy.sprite.x,enemy.sprite.y), Vector2(player.sprite.x, player.sprite.y)))
    }

    override val probability = 1.0
    override fun condition(): Boolean {
        val distanceBetween =  distance(Vector2(player.sprite.x, player.sprite.y), Vector2(enemy.sprite.x,enemy.sprite.y))
        return  distanceBetween >= distanceToStop
    }

}