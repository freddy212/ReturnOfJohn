package com.mygdx.game.UI.Dialogue

import com.badlogic.gdx.math.Polygon

class DialogueBox {

    val polygon = Polygon()

    init {
        polygon.vertices = floatArrayOf(0f,0f,400f,0f,450f,-50f,500f,0f,500f,100f,0f,100f)
    }
}