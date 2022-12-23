package com.mygdx.game.GameObjects.MoveableEntities.Characters.Enemies.Bosses.SandGhost

import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.Enemy
import com.mygdx.game.Enums.Direction
import com.mygdx.game.Enums.getDirectionUnitVector
import com.mygdx.game.Interfaces.EnemyAction

class ExtendArms(val sandHand1: SandHand, val sandHand2: SandHand, val sandGhost: SandGhost): EnemyAction() {

    private var counter = 0
    val modifier = 3f
    override val framesToBlock = 179
    val changeTime = framesToBlock / 2 + 1

    override fun executeEnemyAction() {
        if(counter == 0) {
            sandGhost.unitVectorDirection = Vector2(0f, 0f)
        }
        var amount = 0f
        if(counter <= changeTime){
            amount = counter.toFloat()
        }
        else{
            amount = (changeTime - (counter % changeTime)).toFloat()
        }
        sandHand1.radius = sandHand1.baseRadius + (amount * modifier)
        sandHand2.radius = sandHand2.baseRadius + (amount * modifier)
        counter++
    }

    override val probability: Double
        get() = if(active) 1.0 else 0.01

    override val shouldBlock = true

    override fun condition(): Boolean {
        return sandGhost.health * 2 <= sandGhost.maxHealth
    }

    override fun cleanUp() {
        counter = 0
    }

}