package com.mygdx.game.Managers

import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.AbstractClasses.MoveableObject
import com.mygdx.game.Exceptions.PlayerOutOfBoundsException
import com.mygdx.game.Interfaces.Area
import com.mygdx.game.Interfaces.AreaIdentifier
import com.mygdx.game.LocationImpl
import com.mygdx.game.camera
import com.mygdx.game.player

class LocationManager {
    companion object{
        lateinit var activeArea: Area
        lateinit var locations : List<LocationImpl>
        lateinit var currentLocation: LocationImpl
        lateinit var ActiveLocations: List<LocationImpl>
        lateinit var  ActiveGameObjects: List<GameObject>
        lateinit var ActiveMoveableEntities: List<MoveableObject>
        fun frameAction(){
            LocationFrameTasks()
            ActiveGameObjects.forEach{it.frameTask()}
        }
        fun LocationFrameTasks(){
            locations = activeArea.locations
            var findPlayerLocation = locations.find{ x -> x.sprite.boundingRectangle.contains(Vector2(camera.position.x, camera.position.y)) }
            currentLocation = findPlayerLocation ?: throw PlayerOutOfBoundsException()
            ActiveLocations = (listOf(currentLocation) + currentLocation.adjacentLocations)
            ActiveGameObjects = ActiveLocations.flatMap { x -> x.gameObjects } + ActiveLocations + player
            ActiveMoveableEntities =  ActiveGameObjects.filter {it is MoveableObject}.map { it as MoveableObject}
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