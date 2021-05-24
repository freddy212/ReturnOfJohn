package com.mygdx.game.Managers

import com.badlogic.gdx.math.Vector2
import com.mygdx.game.*
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.Interfaces.*
import com.mygdx.game.Locations.DefaultLocation
import com.mygdx.game.SaveHandling.savePlayerStates

class LocationManager {
    companion object{
        lateinit var activeArea: Area
        lateinit var defaultLocations : List<DefaultLocation>
        var oldDefaultLocation: DefaultLocation
        lateinit var activeDefaultLocations: List<DefaultLocation>
        lateinit var MoveCollitionGameObjects: List<GameObject>
        lateinit var ButtonCollitionGameObjects: List<GameObject>
        lateinit var EveryFrameCollitionGameObjects: List<GameObject>
        lateinit var currentDefaultLocation: DefaultLocation
        var  ActiveGameObjects: List<GameObject>
        init {
            crossLocationGameObjects.add(player)
            oldDefaultLocation = DefaultLocation(Vector2(0f,0f),Vector2(0f,0f))
            ActiveGameObjects = listOf()
        }
        fun frameAction(){
            LocationFrameTasks()
            ActiveGameObjects.forEach{it.frameTask()}
        }
        fun LocationFrameTasks(){
            defaultLocations = activeArea.defaultLocations
            val findPlayerLocation = defaultLocations.find{ x -> x.sprite.boundingRectangle.contains(Vector2(player.sprite.x, player.sprite.y)) } ?: oldDefaultLocation
            val newLocation = findPlayerLocation

            if(oldDefaultLocation != newLocation) {
                currentDefaultLocation = newLocation
                oldDefaultLocation = newLocation
                activeDefaultLocations = (listOf(oldDefaultLocation) + oldDefaultLocation.adjacentDefaultLocations)
                val oldActiveGameObjects = ActiveGameObjects
                ActiveGameObjects = activeDefaultLocations.flatMap { x -> x.gameObjects } + activeDefaultLocations + crossLocationGameObjects.List
                val newGameObjects = ActiveGameObjects - oldActiveGameObjects
                val oldGameObjects = oldActiveGameObjects - ActiveGameObjects
                newGameObjects.forEach{it.onLocationEnterActions.forEach { it() }}
                oldGameObjects.forEach {it.onLocationExitActions.forEach { it() }}
                savePlayerStates()
            }
            ActiveGameObjects = activeDefaultLocations.flatMap { x -> x.gameObjects } + activeDefaultLocations + crossLocationGameObjects.List
            MoveCollitionGameObjects = ActiveGameObjects.filter{x -> x.collition is MoveCollition}
            ButtonCollitionGameObjects = ActiveGameObjects.filter { x -> x.collition is KeyPressedCollition }
            EveryFrameCollitionGameObjects = ActiveGameObjects.filter { x -> x.collition is EveryFrameCollition }
            //Can be optimized at some point
        }
        fun SetArea(area: Area){
            activeArea = area
        }
        fun findLocation(name: String, areaIdentifier: AreaIdentifier): DefaultLocation {
            val area = AreaManager.getArea(areaIdentifier)
            return area.defaultLocations.find { x -> x.locationName == name }!!
        }
    }
}