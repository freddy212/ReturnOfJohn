package com.mygdx.game.GameObjects

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Polygon
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.Collitions.IllegalMoveCollition
import com.mygdx.game.InitSprite

class House(x:Float,y:Float,width:Float,height:Float): GameObject(Vector2(x,y), Vector2(width,height)) {
    val houseTexture = Texture("House.png")

    override val sprite = InitSprite(houseTexture)
    override val polygon = Polygon()
    lateinit var door: Door
    override val collition = IllegalMoveCollition
    init {
        this.door = Door(Vector2(this.sprite.x + this.sprite.width / 4, this.sprite.y),Vector2(this.sprite.width / 2, this.sprite.height / 3))
        polygon.vertices = floatArrayOf(x,y,x,y + height / 2,
                                x + width, y + height / 2,
                                x + width,y, this.door.x + this.door.width, this.door.y,
                                this.door.x + this.door.width, this.door.y + this.door.height,
                                this.door.x, this.door.y + this.door.height,
                                this.door.x, this.door.y)
        ChildrenGameObjects.add(this.door)
    }
}