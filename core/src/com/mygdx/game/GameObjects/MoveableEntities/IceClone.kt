package com.mygdx.game.GameObjects.MoveableEntities

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.DefaultMovement
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.AbstractClasses.MoveableObject
import com.mygdx.game.Collitions.IceCloneCollition
import com.mygdx.game.DefaultTextureHandler
import com.mygdx.game.EdgeOfLocationStrategies.NoAction
import com.mygdx.game.Enums.Layer
import com.mygdx.game.Interfaces.MovementStrategy
import com.mygdx.game.Locations.DefaultLocation

class IceClone(Position: Vector2, size: Vector2, defaultLocation: DefaultLocation?) :
    MoveableObject(Position, size, defaultLocation) {
    override var baseSpeed = 0f
    override val movementStrategy = DefaultMovement(NoAction())
    override var unitVectorDirection = Vector2(0f,0f)
    override val texture = DefaultTextureHandler.getTexture("man.png")
    override val layer = Layer.ONGROUND
    override val collition = IceCloneCollition()

    init {
        sprite.setAlpha(0.8f)
    }

}