package com.mygdx.game.Managers

import com.badlogic.gdx.math.Vector2
import com.mygdx.game.*
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.Interfaces.*
import com.mygdx.game.Locations.DefaultLocation
import com.mygdx.game.Managers.AreaManager.Companion.activeArea
import com.mygdx.game.SaveHandling.savePlayerStates

class LocationManager {
    companion object{
        lateinit var defaultLocations : List<DefaultLocation>
        var oldDefaultLocation: DefaultLocation
        var activeDefaultLocations: Set<DefaultLocation> = setOf()
        lateinit var MoveCollitionGameObjects: List<GameObject>
        lateinit var ButtonCollitionGameObjects: List<GameObject>
        lateinit var newDefaultLocation: DefaultLocation
        var  ActiveGameObjects: List<GameObject>
        init {
            oldDefaultLocation = DefaultLocation(Vector2(0f,0f),Vector2(0f,0f))
            ActiveGameObjects = listOf()
        }
        fun frameAction(){
            LocationFrameTasks()
            ActiveGameObjects.forEach{it.frameTask()}
        }
        fun LocationFrameTasks(){
            defaultLocations = activeArea.defaultLocations
            newDefaultLocation = defaultLocations.find{ x -> x.sprite.boundingRectangle.contains(Vector2(player.sprite.x + player.width / 2, player.sprite.y + player.height / 2)) } ?: oldDefaultLocation

            if(oldDefaultLocation != newDefaultLocation) {
                changeLocation()
                savePlayerStates()
            }
            ActiveGameObjects = activeDefaultLocations.flatMap { x -> x.gameObjects.List } + activeDefaultLocations
            MoveCollitionGameObjects = ActiveGameObjects.filter{x -> x.collition is MoveCollition && x.shouldCollide}
            ButtonCollitionGameObjects = ActiveGameObjects.filter { x -> x.collition is KeyPressedCollition && x.shouldCollide }
            //Can be optimized at some point
        }
        fun changeLocation(){
            oldDefaultLocation.removeGameObject(player)
            newDefaultLocation.addGameObject(player)
            oldDefaultLocation = newDefaultLocation
            activeDefaultLocations = (setOf(oldDefaultLocation) + oldDefaultLocation.adjacentDefaultLocations)
            val oldActiveGameObjects = ActiveGameObjects
            ActiveGameObjects = activeDefaultLocations.flatMap { x -> x.gameObjects.List } + activeDefaultLocations
            val newGameObjects = ActiveGameObjects - oldActiveGameObjects.toSet()
            val oldGameObjects = oldActiveGameObjects - ActiveGameObjects.toSet()
            newGameObjects.forEach{
                it.onLocationEnterActions.forEach { it() }
            }
            oldGameObjects.forEach {it.onLocationExitActions.forEach { it(newDefaultLocation) }}
        }
        fun findLocation(name: String, areaIdentifier: AreaIdentifier): DefaultLocation {
            val area = AreaManager.getArea(areaIdentifier)
            return area.defaultLocations.find { x -> x.locationName == name }!!
        }
    }
}