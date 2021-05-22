package com.mygdx.game.Locations

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.Enums.Layer
import com.mygdx.game.Interfaces.LocationDataStrategy
import com.mygdx.game.font

class DefaultLocation(size: Vector2, Position: Vector2, private val initGameObjectsFunction: () -> List<GameObject> = {listOf()},
                      locationStrategy: LocationDataStrategy = DefaultLocationData()): GameObject(Position,size){
    override val texture: Texture = locationStrategy.texture
    private val AdjacentLocations = mutableListOf<DefaultLocation>()
    private val ChildrenGameObjects: MutableList<GameObject> = mutableListOf()
    val adjacentDefaultLocations : List<DefaultLocation>
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
        val objects = initGameObjectsFunction()
        objects.forEach{x -> addGameObject(x)}
    }
    fun addAdjacentLocation(defaultLocation: DefaultLocation){
        AdjacentLocations.add(defaultLocation)
    }
}