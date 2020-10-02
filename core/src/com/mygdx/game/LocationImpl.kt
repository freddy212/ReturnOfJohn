package com.mygdx.game

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.AbstractClasses.Location

class LocationImpl(size: Vector2, Position: Vector2, private val objectCreationMethod: () -> List<GameObject> = {listOf()}): GameObject(Position,size),Location{
    private val AdjacentLocations = mutableListOf<LocationImpl>()
    val adjacentLocations : List<LocationImpl>
        get() = AdjacentLocations.toList()
    var locationName = ""
    val gameObjects: List<GameObject>
      get() = ChildrenGameObjects.toList()

    val texture = Texture("MainB.jpg")
    override val sprite = InitSprite(texture)
    init {
        texture.setFilter(Texture.TextureFilter.Linear,Texture.TextureFilter.Linear)
        texture.setWrap(Texture.TextureWrap.Repeat,Texture.TextureWrap.MirroredRepeat)
    }

    fun renderObjects(batch: PolygonSpriteBatch){
        gameObjects.forEach{x -> x.render(batch)}
    }
    override fun initLocation() {
        val objects = objectCreationMethod()
        objects.forEach{x -> addGameObject(x)}
    }
    fun addAdjacentLocation(location: LocationImpl){
        AdjacentLocations.add(location)
    }
}