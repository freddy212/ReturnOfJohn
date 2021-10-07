package com.mygdx.game.AbstractClasses

import com.badlogic.gdx.math.Vector2
import com.mygdx.game.Collitions.IllegalMoveCollition
import com.mygdx.game.DefaultTextureHandler
import com.mygdx.game.Enums.Layer
import com.mygdx.game.Locations.DefaultLocation
import com.mygdx.game.SaveHandling.DefaultRemoveObjectSaveState
import com.mygdx.game.SaveState.SaveStateEntity
import com.mygdx.game.Timer.DefaultTimer
import com.mygdx.game.Trimer.DelayTimer
import com.mygdx.game.Utils.RectanglePolygon
import com.mygdx.game.isPolygonsColliding
import com.mygdx.game.plus
import com.mygdx.game.times

abstract class Generator(Position: Vector2, size: Vector2, defaultLocation: DefaultLocation?, val unitVectorDirection: Vector2, timeUntilFire: Float = 0f, shootCoolDown:Float = 3f) :
    GameObject(Position, size, defaultLocation),
    RotationalObject by DefaultRotationalObject(),
    SaveStateEntity by DefaultRemoveObjectSaveState() {

    override val texture = DefaultTextureHandler.getTexture("BoulderGenerator.png")
    override val layer = Layer.ONGROUND
    override val collition = IllegalMoveCollition

    val projectileTimer = DefaultTimer(shootCoolDown)
    val delayTimer = DelayTimer(timeUntilFire)

    abstract fun generateProjectile()
    init {
        polygon.setOrigin(sprite.x + sprite.originX,sprite.y + sprite.originY)
        setRotation(unitVectorDirection,this, 0f)
        onLocationEnterActions.add {delayTimer.resetDelay()}
    }
    fun getDistanceFromGenerator(unitVectorDirection: Vector2): Vector2 {
        var distance = 1f
        while(isPolygonsColliding(this.polygon,
                RectanglePolygon(Vector2(Position + (unitVectorDirection * distance)),size.x,size.y)
            )
        ){
            distance +=1
        }
        return unitVectorDirection * distance
    }

    override fun frameTask(){
        super.frameTask()
        if(delayTimer.getTimeHasPassed()){
            if(projectileTimer.tryUseCooldown()){
                generateProjectile()
            }
        }
    }

}
