package com.mygdx.game.Interfaces

import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch
import com.badlogic.gdx.graphics.g3d.ModelInstance
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.math.Vector3

interface ModelInstanceHandler {
    fun setPosition(position: Vector3)
    fun setRotation(angle: Float, position: Vector3)
    fun render(batch: PolygonSpriteBatch)
}
