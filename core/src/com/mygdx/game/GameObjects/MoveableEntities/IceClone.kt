package com.mygdx.game.GameObjects.MoveableEntities

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.DefaultMovement
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.AbstractClasses.MoveableObject
import com.mygdx.game.Collitions.IceCloneCollition
import com.mygdx.game.DefaultTextureHandler
import com.mygdx.game.EdgeOfLocationStrategies.NoAction
import com.mygdx.game.Enums.Layer
import com.mygdx.game.GetCollidingObjects
import com.mygdx.game.Interfaces.AreaEntranceCollition
import com.mygdx.game.Interfaces.MovementStrategy
import com.mygdx.game.Locations.DefaultLocation
import com.mygdx.game.Managers.LocationManager

class IceClone(Position: Vector2, size: Vector2, defaultLocation: DefaultLocation?) :
    MoveableObject(Position, size, defaultLocation) {
    override var baseSpeed = 0f
    override val movementStrategy = DefaultMovement(NoAction())
    override var unitVectorDirection = Vector2(0f,0f)
    override val texture = DefaultTextureHandler.getTexture("player.png")
    override val layer = Layer.ONGROUND
    override val collition = IceCloneCollition(this)

    init {
        sprite.setColor(Color.BLUE)
        sprite.setAlpha(0.6f)
    }

    override fun removeFromLocation() {
        val collidingObjects = GetCollidingObjects(this, polygon, LocationManager.ActiveGameObjects)
        val collidingAreaObjects = collidingObjects.map { it.collition }.filterIsInstance<AreaEntranceCollition>()
        collidingAreaObjects.forEach {
            it.movedOutside(this)
        }
        super.removeFromLocation()
    }

}