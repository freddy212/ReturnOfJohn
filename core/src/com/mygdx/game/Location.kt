package com.mygdx.game

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.GameObject

class Location(floor: Texture, size: Vector2, Position: Vector2): GameObject(Position,size){
    val locationSprite  = Sprite(floor)
    var locationName = ""
    private val GameObjects = mutableListOf<GameObject>()
    val gameObjects: List<GameObject>
    get() = GameObjects.toList()
    init{
        locationSprite.setSize(size.x,size.y)
        locationSprite.setPosition(Position.x,Position.y)
    }

    override val spritesToRender: List<Sprite>
        get() = listOf(locationSprite)
    fun addGameObject(gameObject: GameObject){
        GameObjects.add(gameObject)
    }
    override fun render(batch: PolygonSpriteBatch){
        super.render(batch)
        gameObjects.forEach{x -> x.render(batch)}
    }

}