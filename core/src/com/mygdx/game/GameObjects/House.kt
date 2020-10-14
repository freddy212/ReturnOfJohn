package com.mygdx.game.GameObjects

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch
import com.badlogic.gdx.math.Polygon
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.*
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.Collitions.IllegalMoveCollition
import com.mygdx.game.Enums.Direction
import com.mygdx.game.Enums.Layer
import com.mygdx.game.Interfaces.AreaIdentifier

class House(Position: Vector2, size: Vector2,location: LocationImpl): GameObject(middleOfObject(Position, size), size,location) {
    override val texture = Texture("House.png")
    override val polygon = Polygon()
    lateinit var door: Door
    override val collition = IllegalMoveCollition
    override val layer = Layer.AIR
    init {
        this.door = Door(Vector2(this.sprite.x + this.sprite.width / 4, this.sprite.y),Vector2(this.sprite.width / 2, this.sprite.height / 3),
                Texture("Door.png"),AreaIdentifier.NOTIMPLEMENTED, doorMainAreaAndDungeonConnection,Direction.UP,location)
        polygon.vertices = floatArrayOf(x,y,x,y + height / 2,
                                x + width, y + height / 2,
                                x + width,y, this.door.x + this.door.width, this.door.y,
                                this.door.x + this.door.width, this.door.y + this.door.height,
                                this.door.x, this.door.y + this.door.height,
                                this.door.x, this.door.y)
        location.addGameObject(door)
    }
    constructor(x:Float,y:Float,width:Float,height:Float,location: LocationImpl): this(Vector2(x,y),Vector2(width,height),location)
    override fun render(batch: PolygonSpriteBatch) {
        sprite.draw(batch)
        door.sprite.draw(batch)
    }
}