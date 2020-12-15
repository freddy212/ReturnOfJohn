package com.mygdx.game.UI

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch
import com.mygdx.game.UI.QuestPanel.QuestContainer
import com.mygdx.game.UI.QuestPanel.QuestPanel

class UIRenderer {
    val uiBatch = PolygonSpriteBatch()

    lateinit var questPanel: QuestPanel

    init {
        val firstQuest = QuestContainer(Texture("sensor.png"),uiBatch)
        val secondQuest = QuestContainer(Texture("sensor.png"),uiBatch)
        val thirdQuest = QuestContainer(Texture("sensor.png"),uiBatch)

        questPanel = QuestPanel(listOf(firstQuest,secondQuest,thirdQuest))
    }

    fun render(){
        uiBatch.begin()
        questPanel.renderQuestIcons()
        uiBatch.end()
    }
}