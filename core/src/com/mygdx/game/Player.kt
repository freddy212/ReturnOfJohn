package com.mygdx.game

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.math.Polygon
import com.badlogic.gdx.math.Vector2

class Player : MoveableEntity(){
    override val sprite =  Sprite(Texture("man.png"))
    override var speed = 5f
    init {
        val scale = 0.3f
        sprite.setSize(sprite.width * scale, sprite.height * scale)
        //sprite.setPosition(0f,0f)
        sprite.setOriginCenter()
    }
}