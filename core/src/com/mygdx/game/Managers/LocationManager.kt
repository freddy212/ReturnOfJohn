package com.mygdx.game.Managers

import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.Center
import com.mygdx.game.Exceptions.PlayerOutOfBoundsException
import com.mygdx.game.Interfaces.Area
import com.mygdx.game.LocationImpl
import com.mygdx.game.RenderGraph.Companion.addToSceneGraph
import com.mygdx.game.camera

class LocationManager {
    companion object{
        lateinit var activeArea: Area
        val locations : List<LocationImpl>
            get() = activeArea.locations
        val ActiveGameObjects: List<GameObject>
            get() = ActiveLocations.flatMap { x -> x.gameObjects } + ActiveLocations
        lateinit var ActiveLocations: List<LocationImpl>
        lateinit var currentLocation: LocationImpl
        fun frameAction(){
            SetActiveLocations()
            ActiveGameObjects.forEach{addToSceneGraph(it)}
        }
        fun SetActiveLocations(){
            var FindPlayerLocation = locations.find{ x -> x.sprite.boundingRectangle.contains(Vector2(camera.position.x, camera.position.y)) }
            currentLocation = FindPlayerLocation ?: throw PlayerOutOfBoundsException()
            ActiveLocations = (listOf(currentLocation) + currentLocation.adjacentLocations)
        }
        fun SetArea(area: Area){
            activeArea = area
        }
        fun findLocation(name: String): LocationImpl {
            return locations.find { x -> x.locationName == name }!!
        }
    }
}