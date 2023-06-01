package com.mygdx.game.UI.Sign

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.Center
import com.mygdx.game.DefaultTextureHandler
import com.mygdx.game.Enums.Layer
import com.mygdx.game.Interfaces.Renderable
import com.mygdx.game.font
import com.mygdx.game.plus

class SignText(val text: String): Renderable {
    override val layer = Layer.AIR
    val sprite = Sprite(DefaultTextureHandler.getTexture("SignText.png"))

    init {
        sprite.scale(4f)
    }

    override fun render(batch: PolygonSpriteBatch) {
        val boxPos = Center + Vector2(-50f, 145f)
        sprite.setPosition(boxPos.x, boxPos.y)
        sprite.draw(batch)
        font.draw(batch, text, sprite.x - 150f, sprite.y + (sprite.height / 2))
    }


}