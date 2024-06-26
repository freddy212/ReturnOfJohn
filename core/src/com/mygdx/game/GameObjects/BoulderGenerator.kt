package com.mygdx.game.GameObjects

import com.badlogic.gdx.math.Vector2
import com.mygdx.game.*
import com.mygdx.game.AbstractClasses.DefaultRotationalObject
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.AbstractClasses.RotationalObject
import com.mygdx.game.Collitions.IllegalMoveCollition
import com.mygdx.game.Enums.Layer
import com.mygdx.game.Events.DefaultEvent
import com.mygdx.game.GameObjects.MoveableEntities.Projectiles.Boulder
import com.mygdx.game.Locations.DefaultLocation
import com.mygdx.game.ObjectProperties.Fire
import com.mygdx.game.SaveHandling.DefaultRemoveObjectSaveState
import com.mygdx.game.SaveState.SaveStateEntity
import com.mygdx.game.Timer.DefaultTimer
import com.mygdx.game.Trimer.DelayTimer
import com.mygdx.game.Utils.RectanglePolygon

class BoulderGenerator(Position: Vector2, size: Vector2, val unitVectorDirection: Vector2, defaultLocation: DefaultLocation,
                       timeUntilFire: Float = 0f, shootCoolDown:Float = 3f, val genereateFireBoulder:Boolean = false): GameObject(Position, size,defaultLocation),RotationalObject by DefaultRotationalObject(),
                                                                             SaveStateEntity by DefaultRemoveObjectSaveState(){
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
        val Position = Vector2(this.sprite.x + this.sprite.width/2,this.sprite.y + this.sprite.height /2) + getBoulderDistanceFromGenerator(unitVectorDirection)
        val boulder = Boulder(Position,Vector2(size.x - (size.x / 10) ,size.y - (size.y / 10)),defaultLocation,Vector2(unitVectorDirection.x,unitVectorDirection.y))
        if(genereateFireBoulder) boulder.properties.add(Fire(DefaultEvent(),boulder))
        defaultLocation!!.addGameObject(boulder)
    }
    fun getBoulderDistanceFromGenerator(unitVectorDirection: Vector2):Vector2{
        var distance = 1f
        while(isPolygonsColliding(this.polygon,
                RectanglePolygon(Vector2(Position + (unitVectorDirection * distance)),size.x,size.y)
            )){
            distance +=1
        }
        return unitVectorDirection * distance
    }
}