package com.mygdx.game

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.MoveableEntity

class Player : MoveableEntity(){
    override val sprite =  Sprite(Texture("man.png"))
    override var speed = 15f
    var direction = Vector2(0f,0f)
    init {
        val scale = 0.3f
        sprite.setSize(sprite.width * scale, sprite.height * scale)
        sprite.setPosition(0f,0f)
        sprite.setOriginCenter()
    }
}