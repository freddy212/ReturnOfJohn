package com.mygdx.game.UI.Actions

import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.Center
import com.mygdx.game.Enums.Direction
import com.mygdx.game.Enums.Layer
import com.mygdx.game.Interfaces.Renderable
import com.mygdx.game.Managers.UIRendererManager
import com.mygdx.game.Utils.RenderGraph
import com.mygdx.game.font

class RenderKeyAction: Renderable {
    override val layer = Layer.FOREGROUND
    var textToShow = ""
    val textFont = BitmapFont()

    init {
        textFont.data.setScale(4f)
    }

    override fun render(batch: PolygonSpriteBatch) {
        textFont.draw(batch,textToShow, Center.x - 50f, Center.y + 200f)
    }

    fun renderKeyAction(key: Int){
        textToShow =  when(key){
            Input.Keys.SPACE -> "SPACE"
            Input.Keys.B -> "B"
            else -> "undefined"
        }
        UIRendererManager.addToUIGraph(this)
    }
}