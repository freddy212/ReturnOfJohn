package com.mygdx.game

import com.badlogic.gdx.math.Polygon
import com.badlogic.gdx.math.Vector2

class RectanglePolygon(startPos: Vector2, width: Float, height:Float) : Polygon() {
    init {
        val vertices = floatArrayOf(startPos.x, startPos.y,
                startPos.x, startPos.y + height,
                startPos.x + width, startPos.y + height,
                startPos.x + width, startPos.y)
        this.vertices = vertices
        }
    }