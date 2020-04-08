package com.mygdx.game

import com.badlogic.gdx.math.Polygon
import com.badlogic.gdx.math.Vector2

fun Polygon.contains(polygon: Polygon): Boolean{
    val points = getPolygonPoints(polygon)
    for(point in points){
        if(!(this.contains(point))){
            return false
        }
    }
    return true
}