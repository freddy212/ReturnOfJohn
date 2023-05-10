package com.mygdx.game.AbstractClasses

import com.badlogic.gdx.math.Vector2
import com.mygdx.game.GameObjects.MoveableEntities.Characters.Player
import com.mygdx.game.Interfaces.AreaEntranceCollition
import com.mygdx.game.Interfaces.MovementStrategy
import com.mygdx.game.Locations.DefaultLocation
import com.mygdx.game.Managers.LocationManager
import com.mygdx.game.handleMoveCollitions
import com.mygdx.game.player
import com.mygdx.game.plus
import com.mygdx.game.times

abstract class MoveableObject(Position: Vector2, size: Vector2, defaultLocation: DefaultLocation?) :
    GameObject(Position, size, defaultLocation) {
    abstract var baseSpeed: Float
    abstract val movementStrategy: MovementStrategy
    private var canMove = true
    abstract var unitVectorDirection: Vector2
    var moveModifier: Vector2 = Vector2(0f, 0f)
    var currentSpeed: Float? = null

    init {
        onLocationEnterActions.add(::resetObject)
    }

    open fun move(unitVectorDirection: Vector2): Boolean {
        if (canMove) {
            val nextIncrement = unitVectorDirection * this.getCurrentSpeed()
            val moveSuccessfull = movementStrategy.moveEntity(this, nextIncrement + moveModifier)
            return moveSuccessfull
        } else {
            return false
        }
    }

    fun freezeMoving() {
        canMove = false
    }

    fun enableMoving() {
        canMove = true
    }

    fun canMove(): Boolean {
        return canMove
    }

    private fun resetObject() {
        if (this is Player) {
            this.setPosition(Vector2(sprite.x, sprite.y))
        } else {
            this.setPosition(startingPosition)
        }
    }

    fun getCurrentSpeed(): Float {
        return currentSpeed ?: baseSpeed
    }

    fun setCurrentSpeed(float: Float) {
        currentSpeed = float
    }

    override fun removeFromLocation() {
        super.removeFromLocation()
    }

}