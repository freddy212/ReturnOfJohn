package com.mygdx.game

import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.GameObject

class LocationManager {
    companion object{
        val BoundingAreas = mutableListOf<Rectangle>()
        val locations : List<LocationImpl>
            get() = Locations.toList()
        val ActiveGameObjects: List<GameObject>
            get() = ActiveLocations.flatMap { x -> x.gameObjects }
        private val Locations = mutableListOf<LocationImpl>()
        private lateinit var ActiveLocations: List<LocationImpl>
        fun addLocation(location:LocationImpl){
            BoundingAreas.add(location.sprite.boundingRectangle)
            location.locationName = "location" + (Locations.size + 1)
            Locations.add(location)
        }
        fun findLocation(name: String): LocationImpl{
            return locations.find { x -> x.locationName == name }!!
        }
        fun SetActiveLocations(){
            val currentLocation = locations.find{
                x -> x.sprite.boundingRectangle.contains(Vector2(camera.position.x, camera.position.y)) }!!
            ActiveLocations = (listOf(currentLocation) + currentLocation.adjacentLocations)
        }
        fun drawTerrain(batch: PolygonSpriteBatch){
            SetActiveLocations()
           for(location in ActiveLocations){
               location.render(batch)
               font.draw(batch,location.locationName, location.topleft.x + 50f, location.topleft.y - 50f)
           }
        }
        fun drawObjects(batch: PolygonSpriteBatch){
            for(location in ActiveLocations){
                location.renderObjects(batch)
            }
        }
    }
}