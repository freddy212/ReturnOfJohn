package com.mygdx.game.Managers

import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.AbstractClasses.MoveableObject
import com.mygdx.game.Exceptions.PlayerOutOfBoundsException
import com.mygdx.game.Interfaces.Area
import com.mygdx.game.Interfaces.AreaIdentifier
import com.mygdx.game.Interfaces.ButtonPressedCollition
import com.mygdx.game.Interfaces.MoveCollition
import com.mygdx.game.LocationImpl
import com.mygdx.game.camera
import com.mygdx.game.crossLocationGameObjects
import com.mygdx.game.player

class LocationManager {
    companion object{
        lateinit var activeArea: Area
        lateinit var locations : List<LocationImpl>
        var oldLocation: LocationImpl
        lateinit var ActiveLocations: List<LocationImpl>
        lateinit var MoveCollitionGameObjects: List<GameObject>
        lateinit var ButtonCollitionGameObjects: List<GameObject>
        var  ActiveGameObjects: List<GameObject>
        init {
            crossLocationGameObjects.add(player)
            oldLocation = LocationImpl(Vector2(0f,0f),Vector2(0f,0f))
            ActiveGameObjects = listOf()
        }
        fun frameAction(){
            LocationFrameTasks()
            ActiveGameObjects.forEach{it.frameTask()}
        }
        fun LocationFrameTasks(){
            locations = activeArea.locations
            val findPlayerLocation = locations.find{ x -> x.sprite.boundingRectangle.contains(Vector2(camera.position.x, camera.position.y)) }

            val newLocation = findPlayerLocation ?: throw PlayerOutOfBoundsException()
            if(oldLocation != newLocation) {
                oldLocation = newLocation
                ActiveLocations = (listOf(oldLocation) + oldLocation.adjacentLocations)
                val oldGameObjects = ActiveGameObjects
                ActiveGameObjects = ActiveLocations.flatMap { x -> x.gameObjects } + ActiveLocations + crossLocationGameObjects.List
                val newGameObjects = ActiveGameObjects - oldGameObjects
                newGameObjects.forEach{x -> x.initOnLocation()}
            }
            ActiveGameObjects = ActiveLocations.flatMap { x -> x.gameObjects } + ActiveLocations + crossLocationGameObjects.List
            MoveCollitionGameObjects = ActiveGameObjects.filter{x -> x.collition is MoveCollition}
            ButtonCollitionGameObjects = ActiveGameObjects.filter { x -> x.collition is ButtonPressedCollition }
            //Can be optimized at some point
        }
        fun SetArea(area: Area){
            activeArea = area
        }
        fun findLocation(name: String, areaIdentifier: AreaIdentifier): LocationImpl {
            val area = AreaManager.getArea(areaIdentifier)
            return area.locations.find { x -> x.locationName == name }!!
        }
    }
}