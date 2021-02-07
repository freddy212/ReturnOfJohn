package com.mygdx.game.Managers

import com.badlogic.gdx.math.Vector2
import com.mygdx.game.*
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.Interfaces.Area
import com.mygdx.game.Interfaces.AreaIdentifier
import com.mygdx.game.Interfaces.KeyPressedCollition
import com.mygdx.game.Interfaces.MoveCollition

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
            val findPlayerLocation = locations.find{ x -> x.sprite.boundingRectangle.contains(Vector2(player.sprite.x, player.sprite.y)) } ?: oldLocation
            val newLocation = findPlayerLocation

            if(oldLocation != newLocation) {
                oldLocation = newLocation
                ActiveLocations = (listOf(oldLocation) + oldLocation.adjacentLocations)
                val oldActiveGameObjects = ActiveGameObjects
                ActiveGameObjects = ActiveLocations.flatMap { x -> x.gameObjects } + ActiveLocations + crossLocationGameObjects.List
                val newGameObjects = ActiveGameObjects - oldActiveGameObjects
                val oldGameObjects = oldActiveGameObjects - ActiveGameObjects
                newGameObjects.forEach{it.onLocationEnterActions.forEach { it() }}
                oldGameObjects.forEach {it.onLocationExitActions.forEach { it() }}
            }
            ActiveGameObjects = ActiveLocations.flatMap { x -> x.gameObjects } + ActiveLocations + crossLocationGameObjects.List
            MoveCollitionGameObjects = ActiveGameObjects.filter{x -> x.collition is MoveCollition}
            ButtonCollitionGameObjects = ActiveGameObjects.filter { x -> x.collition is KeyPressedCollition }
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