package com.mygdx.game.Managers

import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.mygdx.game.Interfaces.FightableEntity
import com.mygdx.game.UI.QuestPanel.QuestPanel
import com.mygdx.game.UI.ToolTipPanel.ToolTipPanel

class UIRendererManager {
    companion object {

        val uiShapeRenderer = ShapeRenderer()
        val uiBatch = PolygonSpriteBatch()
        val questPanel: QuestPanel = QuestPanel(uiBatch)
        val toolTipPanel: ToolTipPanel = ToolTipPanel(uiBatch)
        fun render() {
            uiBatch.begin()
           // questPanel.renderQuestIcons()
            toolTipPanel.renderToolTips()

            val fightableEntities = LocationManager.ActiveGameObjects.filter {it is FightableEntity}
            fightableEntities.forEach {(it as FightableEntity).healthStrategy.showHealth(it.sprite,it.health,it.maxHealth)}

            uiBatch.end()
        }
    }
}