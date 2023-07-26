package com.mygdx.game.Locations

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.Enums.Layer
import com.mygdx.game.Interfaces.AreaIdentifier
import com.mygdx.game.Interfaces.LocationDataStrategy
import com.mygdx.game.Utils.ResourceList
import com.mygdx.game.font
import java.util.concurrent.locks.Condition

class DefaultLocation(size: Vector2, Position: Vector2, private val initGameObjectsFunction: () -> List<GameObject> = {listOf()},
                     val locationStrategy: LocationDataStrategy = DefaultLocationData()): GameObject(Position,size){
    override var texture: Texture = locationStrategy.texture
    private val AdjacentLocations = mutableSetOf<DefaultLocation>()
    val adjacentDefaultLocations : Set<DefaultLocation>
        get() = AdjacentLocations.toSet()
    val gameObjects = ResourceList<GameObject>()
    override val layer = Layer.BEFOREGROUND
    override val collition = locationStrategy.collition

    lateinit var areaIdentifier: AreaIdentifier
    lateinit var locationName: String
    init {
        texture.setFilter(Texture.TextureFilter.Linear,Texture.TextureFilter.Linear)
        texture.setWrap(Texture.TextureWrap.Repeat,Texture.TextureWrap.MirroredRepeat)
        locationStrategy.initialization(this)
    }
    override fun render(batch: PolygonSpriteBatch){
        locationStrategy.render(batch,sprite)
      //  font.draw(batch,this.locationName, this.topleft.x + 50f, this.topleft.y - 50f)
    }

    fun addGameObject(gameObject: GameObject){
       gameObjects.add(gameObject)
        gameObject.defaultLocation = this
    }
    fun removeGameObject(gameObject: GameObject){
       gameObjects.remove(gameObject)
    }
    fun initLocation() {
        val objects = initGameObjectsFunction()
        objects.forEach{x -> x.addToLocation(this)}
    }
    fun addAdjacentLocation(defaultLocation: DefaultLocation){
        AdjacentLocations.add(defaultLocation)
        defaultLocation.AdjacentLocations.add(this)
    }
    fun removeAdjacentLocation(defaultLocation: DefaultLocation){
        AdjacentLocations.remove(defaultLocation)
        defaultLocation.AdjacentLocations.remove(this)
    }

    fun getAreaSpecificTextureName(defaultTextureName: String = "MainB.jpg"): String{
        return when(this.areaIdentifier){
            AreaIdentifier.FIRELANDSDUNGEON -> "firedungeonfloor.jpg"
            AreaIdentifier.WASTELAND -> "LightSand.jpg"
            AreaIdentifier.FIRELANDS -> "FirelandsFloor.jpg"
            AreaIdentifier.DUNGEONAREA -> "dungeonFloor.jpg"
            AreaIdentifier.ICELANDSDUNGEON -> "IceDungeonFloor.jpg"
            AreaIdentifier.ICELANDS -> "IceLandsFloor.jpg"
            else -> defaultTextureName
        }
    }

    //Credits

    //<a href="https://www.freepik.com/free-photo/detailed-orange-grunge-background-with-splats-stains_10167167.htm#query=ice%20texture&position=1&from_view=keyword&track=ais">Image by kjpargeter</a> on Freepik

}   //<a href="https://www.freepik.com/free-vector/red-cracked-ground-texture-pattern-background_37674363.htm#query=Lava%20ground&position=8&from_view=search&track=ais">Image by vector_corp</a> on Freepik