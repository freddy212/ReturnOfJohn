package com.mygdx.game.UI.ToolTipPanel

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.Managers.TooltipManager
import com.mygdx.game.plus

class ToolTipPanel(val batch: PolygonSpriteBatch) {
    val offset = Vector2(75f,0f)
    fun renderToolTips(){
        var startPoint = Vector2(0f + 50f, Gdx.graphics.height - 100f)
        for(tooltip in TooltipManager.tooltipManager.List.sortedBy { it.key }){
            tooltip.render(startPoint,batch)
            startPoint += offset
        }
    }
}