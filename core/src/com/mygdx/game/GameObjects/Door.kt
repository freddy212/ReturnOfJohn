package com.mygdx.game.GameObjects

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Polygon
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.Enums.Direction
import com.mygdx.game.Enums.Layer
import com.mygdx.game.Interfaces.DirectionalObject
import com.mygdx.game.Interfaces.MoveCollition
import com.mygdx.game.LocationImpl

class Door(Position: Vector2, size: Vector2, texture: Texture, location: LocationImpl,
           override var direction: Direction, override val collition: MoveCollition): GameObject(Position,size,location),DirectionalObject {
    override val texture = texture
    override val layer = Layer.AIR
    override var canChangeDirection = false

    override val polygon = Polygon()

    init {
        polygon.vertices = floatArrayOf(x + size.x / 4, y, x + size.x - size.x / 4, y, x + size.x - size.x / 4, y - 20f, x + size.x / 4, y - 20f)
        if(direction == Direction.DOWN || direction == Direction.LEFT) {
            sprite.setAlpha(0.5f)
        }
    }
}