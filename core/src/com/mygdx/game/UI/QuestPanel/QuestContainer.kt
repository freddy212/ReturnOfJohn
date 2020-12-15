package com.mygdx.game.UI.QuestPanel

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.math.Vector2

class QuestContainer(val texture: Texture, val UIBatch: PolygonSpriteBatch) {
    val sprite = Sprite(texture)
    val exclamationSprite = Sprite(Texture("UI/Exclamation.png"))

    fun render(position: Vector2){
        sprite.setPosition(position.x,position.y)
        exclamationSprite.setPosition(sprite.x + sprite.width + 20f, sprite.y)
        sprite.draw(UIBatch)
        exclamationSprite.draw(UIBatch)
    }
}