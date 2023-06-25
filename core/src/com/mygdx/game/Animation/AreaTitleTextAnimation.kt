package com.mygdx.game.Animation

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch
import com.mygdx.game.Enums.Layer
import com.mygdx.game.Interfaces.AreaIdentifier
import com.mygdx.game.Interfaces.getAreaColor
import com.mygdx.game.Interfaces.getAreaTitleText
import com.mygdx.game.Managers.AreaManager
import com.mygdx.game.font
import com.mygdx.game.player

class AreaTitleTextAnimation(val identifier: AreaIdentifier): DefaultAnimation() {
    override val duration = 240
    override val animationAction = {}
    override var actionFrame = 60
    override val layer = Layer.AIR
    val alphaTime = (duration / 3)
    var alpha = 0f

    val areaText = getAreaTitleText(identifier)
    val color = Color(getAreaColor(identifier))

    val areaChangeTitleFont = BitmapFont()

    init {
        areaChangeTitleFont.data.setScale(5f, 4f)
    }

    override fun render(batch: PolygonSpriteBatch) {
        if (currentFrame <= alphaTime + 1) {
            alpha += 1f / alphaTime
        }

        if(currentFrame > alphaTime * 2){
            alpha -= 1f / alphaTime
        }

        color.a = alpha
        areaChangeTitleFont.color = color
        areaChangeTitleFont.draw(batch,areaText, player.currentPosition().x - 300f , player.currentPosition().y + 250f)
    }
}