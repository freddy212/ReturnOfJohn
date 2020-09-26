package com.mygdx.game.AbstractClasses

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.math.Vector2

abstract class GameObject (val Position: Vector2, val size: Vector2){
    val topleft = Vector2(Position.x,Position.y + size.y)
    val topright = Vector2(Position.x + size.x,Position.y + size.y)
    val bottomright =  Vector2(Position.x + size.x,Position.y)
    val bottomleft = Position
    val x = Position.x
    val y = Position.y
    val width = size.x
    val height = size.y

    val middle: Vector2 = Vector2(topleft.x + (topright.x - topleft.x) / 2,bottomleft.y + (topleft.y - bottomleft.y)/2)
    abstract val spriteToRender: Sprite
    protected val ChildrenGameObjects = mutableListOf<GameObject>()
    open fun render(batch: PolygonSpriteBatch){
        spriteToRender.draw(batch)
    }
    fun addGameObject(gameObject: GameObject){
        ChildrenGameObjects.add(gameObject)
        val gameObjectsToAdd = gameObject.ChildrenGameObjects
        gameObjectsToAdd.forEach{ x -> addGameObject(x)}
    }
}