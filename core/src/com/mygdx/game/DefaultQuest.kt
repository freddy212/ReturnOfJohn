package com.mygdx.game

import com.mygdx.game.Enums.QuestIdentifier
import com.mygdx.game.GameObjects.MoveableEntities.NPC
import com.mygdx.game.Interfaces.Quest
import com.mygdx.game.Managers.QuestManager
import com.mygdx.game.UI.QuestPanel.QuestContainer

class DefaultQuest(giverNPC: NPC,override val questIdentifier: QuestIdentifier): Quest {
    override fun QuestCompleted() {
        QuestManager.QuestManager.remove(this)
    }
    override val questContainer = QuestContainer(giverNPC.texture)
    override fun StartQuest() {
        QuestManager.QuestManager.add(this)
    }

}
