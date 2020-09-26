package com.mygdx.game.GameObjects

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.math.Polygon
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.InitSprite
import com.mygdx.game.TerrainManager.Companion.collitionPolygons
import com.mygdx.game.illegalMoveCollition

class House(x:Float,y:Float,width:Float,height:Float): GameObject(Vector2(x,y), Vector2(width,height)) {
    val houseTexture = Texture("House.png")

    override val spriteToRender = InitSprite(houseTexture)
    val housePolygon = Polygon()
    lateinit var door: Door
    init {
        this.door = Door(Vector2(this.spriteToRender.x + this.spriteToRender.width / 4, this.spriteToRender.y),Vector2(this.spriteToRender.width / 2, this.spriteToRender.height / 3))
        housePolygon.vertices = floatArrayOf(x,y,x,y + height / 2,
                                x + width / 2, y + height,
                                x + width, y + height / 2,
                                x + width,y, this.door.x + this.door.width, this.door.y,
                                this.door.x + this.door.width, this.door.y + this.door.height,
                                this.door.x, this.door.y + this.door.height,
                                this.door.x, this.door.y)
        collitionPolygons.add(Pair(housePolygon,illegalMoveCollition))
        ChildrenGameObjects.add(this.door)
    }
}