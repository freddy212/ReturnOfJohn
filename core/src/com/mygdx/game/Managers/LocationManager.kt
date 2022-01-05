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
        lateinit var activeDefaultLocations: Set<DefaultLocation>
        lateinit var MoveCollitionGameObjects: List<GameObject>
        lateinit var ButtonCollitionGameObjects: List<GameObject>
        lateinit var EveryFrameCollitionGameObjects: List<GameObject>
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
            newDefaultLocation = defaultLocations.find{ x -> x.sprite.boundingRectangle.contains(Vector2(player.sprite.x, player.sprite.y)) } ?: oldDefaultLocation

            if(oldDefaultLocation != newDefaultLocation) {
                oldDefaultLocation.removeGameObject(player)
                newDefaultLocation.addGameObject(player)
                oldDefaultLocation = newDefaultLocation
                activeDefaultLocations = (setOf(oldDefaultLocation) + oldDefaultLocation.adjacentDefaultLocations)
                val oldActiveGameObjects = ActiveGameObjects
                ActiveGameObjects = activeDefaultLocations.flatMap { x -> x.gameObjects } + activeDefaultLocations
                val newGameObjects = ActiveGameObjects - oldActiveGameObjects
                val oldGameObjects = oldActiveGameObjects - ActiveGameObjects
                newGameObjects.forEach{it.onLocationEnterActions.forEach { it() }}
                oldGameObjects.forEach {it.onLocationExitActions.forEach { it(newDefaultLocation) }}
                savePlayerStates()
            }
            ActiveGameObjects = activeDefaultLocations.flatMap { x -> x.gameObjects } + activeDefaultLocations
            MoveCollitionGameObjects = ActiveGameObjects.filter{x -> x.collition is MoveCollition}
            ButtonCollitionGameObjects = ActiveGameObjects.filter { x -> x.collition is KeyPressedCollition }
            EveryFrameCollitionGameObjects = ActiveGameObjects.filter { x -> x.collition is EveryFrameCollition }
            //Can be optimized at some point
        }
        fun findLocation(name: String, areaIdentifier: AreaIdentifier): DefaultLocation {
            val area = AreaManager.getArea(areaIdentifier)
            return area.defaultLocations.find { x -> x.locationName == name }!!
        }
    }
}