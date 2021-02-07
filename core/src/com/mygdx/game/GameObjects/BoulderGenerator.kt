package com.mygdx.game.GameObjects

import com.badlogic.gdx.math.Vector2
import com.mygdx.game.*
import com.mygdx.game.AbstractClasses.DefaultRotationalObject
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.AbstractClasses.RotationalObject
import com.mygdx.game.Collitions.IllegalMoveCollition
import com.mygdx.game.Enums.Layer
import com.mygdx.game.GameObjects.MoveableEntities.Boulder
import com.mygdx.game.Timer.DefaultTimer
import com.mygdx.game.Trimer.DelayTimer

class BoulderGenerator(Position: Vector2, size: Vector2, val unitVectorDirection: Vector2, location: LocationImpl,
                       timeUntilFire: Float = 0f, shootCoolDown:Float = 3f): GameObject(Position, size,location),RotationalObject by DefaultRotationalObject() {
    override val texture = DefaultTextureHandler.getTexture("BoulderGenerator.png")
    override val layer = Layer.ONGROUND
    override val collition = IllegalMoveCollition
    val BoulderTimer = DefaultTimer(shootCoolDown)
    val delayTimer = DelayTimer(timeUntilFire)
    init {
        polygon.setOrigin(sprite.x + sprite.originX,sprite.y + sprite.originY)
        setRotation(unitVectorDirection,this, 0f)
        onLocationEnterActions.add {delayTimer.resetDelay()}
    }
    override fun frameTask(){
        super.frameTask()
        if(delayTimer.getTimeHasPassed()){
            if(BoulderTimer.tryUseCooldown()){
                generateBoulder()
            }
        }
    }

    fun generateBoulder(){
        val Position = Vector2(this.sprite.x + this.sprite.width/2,this.sprite.y + this.sprite.height /2) + (unitVectorDirection * 160f)
        val boulder = Boulder(unitVectorDirection,Position,Vector2( 64 * 2f,64f * 2),location)
        location!!.addGameObject(boulder)
    }
}