package com.mygdx.game.GameObjects

import com.mygdx.game.DefaultTextureHandler
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.DefaultRotationalObject
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.AbstractClasses.RotationalObject
import com.mygdx.game.Collitions.IllegalMoveCollition
import com.mygdx.game.Enums.Direction
import com.mygdx.game.Enums.Layer
import com.mygdx.game.GameObjects.MoveableEntities.Boulder
import com.mygdx.game.LocationImpl
import com.mygdx.game.Timer.DefaultTimer
import com.mygdx.game.Trimer.DelayTimer
import com.mygdx.game.getDirectionFromAngle
import com.mygdx.game.unitVectorToAngle

class BoulderGenerator(Position: Vector2, size: Vector2, val unitVectorDirection: Vector2,location: LocationImpl,
                       timeUntilFire: Float = 0f): GameObject(Position, size,location),RotationalObject by DefaultRotationalObject() {
    override val texture = DefaultTextureHandler.getTexture("BoulderGenerator.png")
    override val layer = Layer.ONGROUND
    override val collition = IllegalMoveCollition
    val BoulderTimer = DefaultTimer(3f)
    val delayTimer = DelayTimer(timeUntilFire)
    init {
       setRotation(unitVectorDirection,this, 0f)
    }
    override fun frameTask(){
        super.frameTask()
        if(delayTimer.getTimeHasPassed()){
            if(BoulderTimer.tryUseCooldown()){
                generateBoulder()
            }
        }
    }

    override var onLocationEnter = {delayTimer.resetDelay()}
    fun generateBoulder(){
        val Position = when(getDirectionFromAngle(angle + 90f)){
            Direction.RIGHT -> Vector2(this.bottomright.x + 96f,this.middle.y)
            Direction.UP -> Vector2(this.middle.x,this.topleft.y + 96f)
            Direction.LEFT -> Vector2(this.bottomleft.x - 96f, this.middle.y)
            Direction.DOWN -> Vector2(this.middle.x, this.bottomleft.y - 96f)
        }
        val boulder = Boulder(unitVectorDirection,Position,Vector2( 64 * 2f,64f * 2),location)
        location!!.addGameObject(boulder)
    }
}