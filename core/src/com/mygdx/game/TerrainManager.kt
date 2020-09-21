package com.mygdx.game

import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.Polygon
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector2

class TerrainManager {
    companion object{
        val BoundingAreas = mutableListOf<Rectangle>()
        val locationSprites = mutableListOf<Location>()
        val collitionPolygons = mutableListOf<Polygon>()
        /*fun getTerrainList(): MutableList<Polygon>{
            return listOfPolygons
        }*/
        fun addTerrain(area:Location){
            BoundingAreas.add(area.locationSprite.boundingRectangle)
            area.locationName = "location" + (locationSprites.size + 1)
            locationSprites.add(area)

        }
        fun addCollitionPolygon(polygon: Polygon){
            collitionPolygons.add(polygon)
        }
        fun drawTerrain(batch: PolygonSpriteBatch){
           for(location in locationSprites){
               location.render(batch)
               font.draw(batch,location.locationName, location.topleft.x + 50f, location.topleft.y - 50f)
           }
        }
    }
}