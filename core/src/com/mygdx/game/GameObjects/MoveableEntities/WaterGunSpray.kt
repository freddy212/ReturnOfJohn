package com.mygdx.game.GameObjects.MoveableEntities

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.DefaultMovement
import com.mygdx.game.AbstractClasses.MoveableObject
import com.mygdx.game.EdgeOfLocationStrategies.NoAction
import com.mygdx.game.Enums.Direction
import com.mygdx.game.Enums.Layer
import com.mygdx.game.Interfaces.MovementStrategy
import com.mygdx.game.LocationImpl

class WaterGunSpray(Position: Vector2, size: Vector2, location: LocationImpl?, override var direction: Direction): MoveableObject(Position, size, location) {
    override var speed = 0f
    override val movementStrategy = DefaultMovement(NoAction())
    override val texture: Texture
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
    override val layer: Layer
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
}