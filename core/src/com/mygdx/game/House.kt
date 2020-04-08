package com.mygdx.game

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Polygon
import com.mygdx.game.TerrainManager.Companion.collitionPolygons

class House(x:Float,y:Float,width:Float,height:Float) {
    private val sprite = Sprite(Texture("House.png"))
    private val DoorS = Sprite(Texture("Door.png"))
    val housePolygon = Polygon()
    init {
        sprite.setSize(width,height)
        sprite.setOriginCenter()
        sprite.setPosition(x,y)
        DoorS.setSize(sprite.width / 2, sprite.height / 3)
        DoorS.setPosition(sprite.x + sprite.width / 4, sprite.y)
        housePolygon.vertices = floatArrayOf(x,y,x,y + height / 2,
                                x + width / 2, y + height,
                                x + width, y + height / 2,
                                x + width,y, DoorS.x + DoorS.width, DoorS.y,
                                DoorS.x + DoorS.width,DoorS.y + DoorS.height,
                                DoorS.x, DoorS.y + DoorS.height,
                                DoorS.x, DoorS.y)
        collitionPolygons.add(housePolygon)
    }
    fun render(batch: PolygonSpriteBatch){
        sprite.draw(batch)
        DoorS.draw(batch)
    }
}