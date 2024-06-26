package com.mygdx.game.GameObjects

import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch
import com.badlogic.gdx.math.Polygon
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.*
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.Collitions.DoorCollition
import com.mygdx.game.Collitions.IllegalMoveCollition
import com.mygdx.game.Enums.Direction
import com.mygdx.game.Enums.Layer
import com.mygdx.game.Interfaces.AreaIdentifier
import com.mygdx.game.Locations.DefaultLocation

class House(Position: Vector2, size: Vector2, defaultLocation: DefaultLocation, doorConnection: DoorConnection, areaIdentifier: AreaIdentifier): GameObject(middleOfObject(Position, size), size,defaultLocation) {
    override val texture = DefaultTextureHandler.getTexture("House.png")
    override val polygon = Polygon()
    var door: Door
    override val collition = IllegalMoveCollition
    override val layer = Layer.AIR
    init {
        val doorPosition = Vector2(this.sprite.x + this.sprite.width / 4, this.sprite.y)
        val doorCollition = DoorCollition(doorPosition,areaIdentifier,doorConnection,Direction.UP)
        this.door = Door(doorPosition,Vector2(this.sprite.width / 2, this.sprite.height / 3),
                DefaultTextureHandler.getTexture("Door.png"),defaultLocation,Direction.UP,doorCollition)
        polygon.vertices = floatArrayOf(x,y,x,y + height / 2,
                                x + width, y + height / 2,
                                x + width,y, this.door.x + this.door.width, this.door.y,
                                this.door.x + this.door.width, this.door.y + this.door.height,
                                this.door.x, this.door.y + this.door.height,
                                this.door.x, this.door.y)
        defaultLocation.addGameObject(door)
    }
    constructor(x:Float, y:Float, width:Float, height:Float, defaultLocation: DefaultLocation, doorConnection: DoorConnection, areaIdentifier: AreaIdentifier): this(Vector2(x,y),Vector2(width,height),defaultLocation,
                                                                                                                                                doorConnection,areaIdentifier)
    override fun render(batch: PolygonSpriteBatch) {
        sprite.draw(batch)
        door.sprite.draw(batch)
    }
}