package com.mygdx.game.Events

import com.mygdx.game.Enums.QuestIdentifier
import com.mygdx.game.Interfaces.Event
import com.mygdx.game.Interfaces.Quest
import com.mygdx.game.Managers.QuestManager

class ClearQuestEvent(val questIdentifier: QuestIdentifier):Event {
    override fun execute() {
       val quest =  QuestManager.QuestManager.List.find{it.questIdentifier == questIdentifier}
       quest?.QuestCompleted()
    }
}