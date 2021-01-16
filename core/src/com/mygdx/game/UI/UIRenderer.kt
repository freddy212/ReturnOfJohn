package com.mygdx.game.UI

import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch
import com.mygdx.game.UI.QuestPanel.QuestPanel
import com.mygdx.game.UI.ToolTipPanel.ToolTipPanel

class UIRenderer {
    val uiBatch = PolygonSpriteBatch()

    lateinit var questPanel: QuestPanel
    lateinit var toolTipPanel: ToolTipPanel
    init {
        questPanel = QuestPanel(uiBatch)
        toolTipPanel = ToolTipPanel(uiBatch)

    }

    fun render(){
        uiBatch.begin()
        questPanel.renderQuestIcons()
        toolTipPanel.renderToolTips()
        uiBatch.end()
    }
}