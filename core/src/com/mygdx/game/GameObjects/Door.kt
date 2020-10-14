package com.mygdx.game.GameObjects

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Polygon
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.Collitions.DoorCollition
import com.mygdx.game.DoorConnection
import com.mygdx.game.Enums.Direction
import com.mygdx.game.Enums.Layer
import com.mygdx.game.Interfaces.AreaIdentifier
import com.mygdx.game.LocationImpl

class Door(Position: Vector2, size: Vector2, texture: Texture,areaId: AreaIdentifier,
           val connection: DoorConnection,triggerDirection:Direction,location:LocationImpl): GameObject(Position,size,location) {
    override val texture = texture
    override val layer = Layer.AIR

    override val polygon = Polygon()

    init {
        polygon.vertices = floatArrayOf(x + size.x / 4, y, x + size.x - size.x / 4, y, x + size.x - size.x / 4, y - 20f, x + size.x / 4, y - 20f)
        if(triggerDirection == Direction.DOWN){
            sprite.setAlpha(0.5f)
        }
    }

    override val collition = DoorCollition(areaId,connection,triggerDirection)
}