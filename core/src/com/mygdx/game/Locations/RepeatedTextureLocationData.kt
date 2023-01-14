package com.mygdx.game.Locations

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.DefaultTextureHandler
import com.mygdx.game.renderRepeatedTexture

class RepeatedTextureLocationData(val baseTextureName: String, val repeatedTexture: Texture): DamageLocationData(baseTextureName) {

    override fun render(batch: PolygonSpriteBatch, sprite: Sprite) {
        super.render(batch, sprite)
        renderRepeatedTexture(batch,repeatedTexture, Vector2(sprite.x,sprite.y),Vector2(sprite.width,sprite.height))
    }
}