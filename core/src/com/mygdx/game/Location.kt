package com.mygdx.game

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.GameObject
import kotlin.math.floor

class Location(val floor: Texture, size: Vector2, Position: Vector2): GameObject(Position,size){
    var locationName = ""
    val gameObjects: List<GameObject>
      get() = ChildrenGameObjects.toList()

    override val spriteToRender: Sprite
        get() = InitSprite(floor)
    override fun render(batch: PolygonSpriteBatch){
        super.render(batch)
        gameObjects.forEach{x -> x.render(batch)}
    }

}