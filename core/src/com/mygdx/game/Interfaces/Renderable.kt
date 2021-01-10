package com.mygdx.game.Interfaces

import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g3d.ModelBatch
import com.mygdx.game.Enums.Layer

interface Renderable {
    val layer: Layer
    fun render(batch: PolygonSpriteBatch)
}