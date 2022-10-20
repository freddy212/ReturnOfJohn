package com.mygdx.game.GameObjects.Hazards.ConveyerBelt

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.DefaultTextureHandler
import com.mygdx.game.Enums.Direction
import com.mygdx.game.Enums.getDirectionUnitVector
import com.mygdx.game.times

class ConveyerBrick(val pos: Vector2, val size: Vector2,val speed: Float,val  direction:Direction, texture: Texture): Sprite(texture) {
    init {
        if(direction == Direction.DOWN){
            this.setPosition(pos.x,pos.y)
        } else if (direction == Direction.UP) {
            this.setPosition(pos.x,pos.y - size.y)
        }
        this.setSize(size.x,size.y)
    }

    fun move(){
        val directionUnitVector = getDirectionUnitVector(direction)
        val nextIncrement = directionUnitVector * speed
        if(direction == Direction.DOWN){
            this.setPosition(this.x + nextIncrement.x, this.y + nextIncrement.y)
            if(this.y <= pos.y - size.y){
                this.setPosition(pos.x,pos.y)
            }
        }else if(direction == Direction.UP){
            this.setPosition(this.x + nextIncrement.x, this.y + nextIncrement.y)
            if(this.y >= pos.y){
                this.setPosition(pos.x,pos.y - size.y)
            }
        }
    }
}