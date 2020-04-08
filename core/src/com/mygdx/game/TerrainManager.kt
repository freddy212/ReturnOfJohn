package com.mygdx.game

import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.Polygon
import com.badlogic.gdx.math.Vector2

class TerrainManager {
    companion object{
        val listOfPolygons = mutableListOf<Polygon>()
        val collitionPolygons = mutableListOf<Polygon>()
        /*fun getTerrainList(): MutableList<Polygon>{
            return listOfPolygons
        }*/
        fun addTerrain(polygon: Polygon){
            listOfPolygons.add(polygon)
        }
        fun addCollitionPolygon(polygon: Polygon){
            collitionPolygons.add(polygon)
        }
        fun drawTerrain(shapeRenderer: ShapeRenderer, cameraPos: Vector2){
            for(poly in listOfPolygons.toMutableList().union(collitionPolygons)){
                poly.setPosition((Center.x - cameraPos.x), (Center.y - cameraPos.y))
                shapeRenderer.polygon(poly.transformedVertices)
            }
        }
    }
}