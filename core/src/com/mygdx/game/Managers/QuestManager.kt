package com.mygdx.game.Managers

import com.mygdx.game.DefaultQuest
import com.mygdx.game.Interfaces.Quest
import com.mygdx.game.ResourceList
import com.mygdx.game.UI.QuestPanel.QuestContainer

class QuestManager {
    companion object{
        val QuestManager = ResourceList<Quest>()
    }
}