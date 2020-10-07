package com.mygdx.game.GameObjects

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Polygon
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.DefaultArea
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.Collitions.DoorCollition
import com.mygdx.game.Enums.Layer
import com.mygdx.game.Interfaces.Area
import com.mygdx.game.Interfaces.AreaIdentifier

class Door(Position: Vector2, size: Vector2, texture: Texture,areaId: AreaIdentifier,
           playerPosAfter: Vector2): GameObject(Position,size) {
    override val texture = texture
    override val layer = Layer.ONGROUND

    override val polygon = Polygon()

    init {
        polygon.vertices = floatArrayOf(x + size.x / 4,y,x + size.x - size.x / 4, y,x + size.x - size.x / 4, y - 20f, x + size.x / 4,y - 20f)
    }
    override val collition = DoorCollition(areaId,playerPosAfter)
}