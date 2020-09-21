package com.mygdx.game.AbstractClasses

import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.math.Vector2

abstract class GameObject (val Position: Vector2, val size: Vector2){
    val topleft = Vector2(Position.x,Position.y + size.y)
    val topright = Vector2(Position.x + size.x,Position.y + size.y)
    val bottomright =  Vector2(Position.x + size.x,Position.y)
    val bottomleft = Position
    val middle: Vector2 = Vector2(topleft.x + (topright.x - topleft.x) / 2,bottomleft.y + (topleft.y - bottomleft.y)/2)
    abstract val spritesToRender: List<Sprite>

    open fun render(batch: PolygonSpriteBatch){
        spritesToRender.forEach { x -> x.draw(batch)}
    }
}