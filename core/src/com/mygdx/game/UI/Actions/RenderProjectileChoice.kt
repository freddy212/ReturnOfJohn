package com.mygdx.game.UI.Actions

import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.CharacterAbility
import com.mygdx.game.DefaultTextureHandler
import com.mygdx.game.Enums.Layer
import com.mygdx.game.Interfaces.Renderable
import com.mygdx.game.Managers.UIRendererManager
import com.mygdx.game.font
import com.mygdx.game.minus
import com.mygdx.game.plus

class RenderProjectileChoice(val abilities: List<CharacterAbility>,val pos: Vector2, val activeIndex: Int): Renderable {
    override val layer = Layer.FOREGROUND
    var textToShow = ""

    val uiCircle = DefaultTextureHandler.getTexture("UICircle.png")
    val uiCircleSprite = Sprite(uiCircle)

    override fun render(uiBatch: PolygonSpriteBatch) {
        var offset = Vector2(0f,0f)
        val uiCirclePos = pos - Vector2(0f,activeIndex * 50f)
        uiCircleSprite.setPosition(uiCirclePos.x, uiCirclePos.y)
        uiCircleSprite.draw(uiBatch)
        for ((index,ability) in abilities.withIndex()) {
            val sprite = Sprite(ability.toolTipTexture)
            sprite.setSize(50f,50f)
            val finalPosition = pos - offset
            sprite.setPosition(finalPosition.x,finalPosition.y)
            sprite.draw(uiBatch)
            offset += Vector2(0f, 50f)
        }
    }
}