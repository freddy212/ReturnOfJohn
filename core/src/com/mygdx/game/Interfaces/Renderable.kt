package com.mygdx.game.Interfaces

import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch
import com.badlogic.gdx.graphics.g2d.Sprite
import com.mygdx.game.Enums.Layer

interface Renderable {
    val layer: Layer
    val sprite: Sprite
    fun render(batch: PolygonSpriteBatch)
}