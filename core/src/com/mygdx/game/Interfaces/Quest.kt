package com.mygdx.game.Interfaces

import com.mygdx.game.Enums.QuestIdentifier
import com.mygdx.game.UI.QuestPanel.QuestContainer

interface Quest {
    fun QuestCompleted()
    val questIdentifier: QuestIdentifier
    fun StartQuest()
    val questContainer: QuestContainer
}