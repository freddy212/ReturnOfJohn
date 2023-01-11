package com.mygdx.game.Interfaces

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch
import com.badlogic.gdx.graphics.g2d.Sprite
import com.mygdx.game.Locations.DefaultLocation

interface LocationDataStrategy {
    val texture: Texture
    val initialization: (DefaultLocation) -> Unit
    val collition: BaseCollition

    fun render(batch: PolygonSpriteBatch, sprite: Sprite){
        sprite.draw(batch)
    }
}