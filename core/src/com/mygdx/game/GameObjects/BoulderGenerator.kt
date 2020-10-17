package com.mygdx.game.GameObjects

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.AddToObjectLocation
import com.mygdx.game.Collitions.IllegalMoveCollition
import com.mygdx.game.Enums.Direction
import com.mygdx.game.Enums.Layer
import com.mygdx.game.GameObjects.MoveableEntities.Boulder
import com.mygdx.game.LocationImpl
import com.mygdx.game.Managers.MovableObjectManager
import com.mygdx.game.Timer.Timer
import com.mygdx.game.Trimer.DelayTimer

class BoulderGenerator(Position: Vector2, size: Vector2, val direction: Direction,location: LocationImpl,
                       timeUntilFire: Float = 0f): GameObject(Position, size,location) {
    override val texture = Texture("BoulderGenerator.png")
    override val layer = Layer.ONGROUND
    override val collition = IllegalMoveCollition
    val BoulderTimer = Timer(3f)
    val delayTimer = DelayTimer(timeUntilFire)
    init {
        sprite.rotation = when (direction) {
            Direction.RIGHT -> 0f
            Direction.UP -> 90f
            Direction.LEFT -> 180f
            Direction.DOWN -> 270f
        }
    }
    override fun frameTask(){
        super.frameTask()
        if(delayTimer.getTimeHasPassed()){
            if(BoulderTimer.tryUseCooldown()){
                generateBoulder()
            }
        }
    }

    override fun initOnLocation() {
        super.initOnLocation()
        delayTimer.resetDelay()
    }
    fun generateBoulder(){
        val Position = when(direction){
            Direction.RIGHT -> Vector2(this.bottomright.x + 96f,this.middle.y)
            Direction.UP -> Vector2(this.middle.x,this.topleft.y + 96f)
            Direction.LEFT -> Vector2(this.bottomleft.x - 96f, this.middle.y)
            Direction.DOWN -> Vector2(this.middle.x, this.bottomleft.y - 96f)
        }
        val boulder = Boulder(direction,Position,Vector2( 64 * 2f,64f * 2),location)
        MovableObjectManager.addMoveableObject(boulder)
    }
}