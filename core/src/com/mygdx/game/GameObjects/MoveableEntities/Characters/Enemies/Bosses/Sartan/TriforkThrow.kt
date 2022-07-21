package com.mygdx.game.GameObjects.MoveableEntities.Characters.Enemies.Bosses.Sartan

import com.badlogic.gdx.math.Vector2
import com.mygdx.game.*
import com.mygdx.game.AbstractClasses.Enemy
import com.mygdx.game.GameObjects.MoveableEntities.Characters.Enemies.Bosses.SandGhost.Sartan
import com.mygdx.game.Interfaces.EnemyAction
import kotlin.math.cos
import kotlin.math.sin

class TriforkThrow(val sartan: Sartan, val trifork: Trifork): EnemyAction() {

    private var counter = 0
    override val framesToBlock = 100
    val changeTime = framesToBlock / 2
    var towards = Vector2()
    var back = Vector2()
    var prevPosition = Vector2()
    val origin = Vector2(sartan.sprite.originX, sartan.sprite.originY)
    val distance = distance(sartan.currentPosition(), sartan.currentPosition() + origin)

    override fun executeEnemyAction(enemy: Enemy) {
        if(counter == 0) {
            //val unitVector = getUnitVectorTowardsPoint(sartan.currentPosition(), player.currentPosition())
            //sartan.setCharacterRotation(unitVector)
            prevPosition = trifork.currentPosition()
            val currentPos = Vector2(75 * cos(Radians(sartan.angle)), 75 * sin(Radians(sartan.angle))) - Vector2(75f,50f)
            trifork.sprite.setOriginCenter()
            trifork.polygon.setOrigin(trifork.initPosition.x + trifork.sprite.originX, trifork.initPosition.y + trifork.sprite.originY)
            trifork.setPosition(trifork.currentPosition() - currentPos)
            //trifork.polygon.setPosition(trifork.sprite.x - trifork.polygon.vertices[0],trifork.sprite.y - trifork.polygon.vertices[1])
            trifork.neutralState = false
            trifork.currentSpeed = 8f
            val goalPos = player.currentPosition()
            towards = getUnitVectorTowardsPoint(trifork.currentPosition(), goalPos)
            back = getOppositeUnitVector(trifork.currentPosition(), goalPos)
            sartan.unitVectorDirection = Vector2(0f,0f)
            sartan.setCharacterRotation(towards)
        }
        if(counter <= changeTime){
            trifork.move(towards)
        }
        else{
            trifork.move(back)
        }
        trifork.rotate(3f)
        counter++
    }

    override val probability: Double
        get() = if(active) 1.0 else 0.01

    override val shouldBlock = true

    override fun cleanUp() {
        counter = 0
        trifork.neutralState = true
        trifork.currentSpeed = sartan.currentSpeed
        trifork.sprite.setOrigin(trifork.origOrigin.x, trifork.origOrigin.y)
        trifork.polygon.setOrigin(trifork.initPosition.x + trifork.sprite.originX, trifork.initPosition.y + trifork.sprite.originY)
        trifork.setPosition(prevPosition)
    }

}