package com.mygdx.game.GameObjects.Other

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Polygon
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.Enums.Direction
import com.mygdx.game.Enums.Layer
import com.mygdx.game.Interfaces.DirectionalObject
import com.mygdx.game.Interfaces.MoveCollition
import com.mygdx.game.Locations.DefaultLocation

class Door(Position: Vector2, size: Vector2, texture: Texture, defaultLocation: DefaultLocation,
           override var direction: Direction, override val collition: MoveCollition): GameObject(Position,size,defaultLocation),DirectionalObject {
    override val texture = texture
    override val layer = Layer.AIR
    override var canChangeDirection = false

    override val polygon = Polygon()

    init {
        if(direction == Direction.UP){
            polygon.vertices = floatArrayOf(x + size.x / 4, y, x + size.x - size.x / 4, y, x + size.x - size.x / 4, y - 20f, x + size.x / 4, y - 20f)
        }
        if(direction == Direction.DOWN) {
            sprite.setPosition(sprite.x,sprite.y - 64f)
            polygon.vertices = floatArrayOf(x + size.x / 4, y + 20f, x + size.x - size.x / 4, y + 20f, x + size.x - size.x / 4, y, x + size.x / 4, y)
        }
        if(direction == Direction.RIGHT){
            polygon.vertices = floatArrayOf(x - size.x / 4, y + 20f, x, y + 20f, x, y + size.y - size.y/4,x - size.x / 4, y + size.y - size.y/4)
        }
        if(direction == Direction.LEFT){
            polygon.vertices = floatArrayOf(x + size.x, y + 20f, x + size.x / 4 + size.x, y + 20f, x + size.x / 4 + size.x, y + size.y - size.y/4,x + size.x, y + size.y - size.y/4)
        }
    }
}