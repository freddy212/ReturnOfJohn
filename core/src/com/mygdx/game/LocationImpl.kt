package com.mygdx.game

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.Enums.Layer
import com.mygdx.game.GameObjects.Wall
import com.mygdx.game.Interfaces.Collition
import com.mygdx.game.Interfaces.LocationStrategy
import com.mygdx.game.Locations.DefaultLocation

class LocationImpl(size: Vector2, Position: Vector2, private val objectCreationMethod: () -> List<GameObject> = {listOf()},
                         val locationStrategy: LocationStrategy = DefaultLocation()): GameObject(Position,size){
    override val texture: Texture = locationStrategy.texture
    private val AdjacentLocations = mutableListOf<LocationImpl>()
    private val ChildrenGameObjects: MutableList<GameObject> = mutableListOf()
    val adjacentLocations : List<LocationImpl>
        get() = AdjacentLocations.toList()
    val gameObjects: List<GameObject>
        get() = ChildrenGameObjects.toList()
    override val layer = Layer.GROUND
    override val collition = locationStrategy.collition
    var locationName = ""
    init {
        texture.setFilter(Texture.TextureFilter.Linear,Texture.TextureFilter.Linear)
        texture.setWrap(Texture.TextureWrap.Repeat,Texture.TextureWrap.MirroredRepeat)

        locationStrategy.initialization(this)
    }
    override fun render(batch: PolygonSpriteBatch){
        this.sprite.draw(batch)
        font.draw(batch,this.locationName, this.topleft.x + 50f, this.topleft.y - 50f)
    }

    fun addGameObject(gameObject: GameObject){
        ChildrenGameObjects.add(gameObject)
    }
    fun removeGameObject(gameObject: GameObject){
        ChildrenGameObjects.remove(gameObject)
    }
    fun initLocation() {
        val objects = objectCreationMethod()
        objects.forEach{x -> addGameObject(x)}
    }
    fun addAdjacentLocation(location: LocationImpl){
        AdjacentLocations.add(location)
    }
}