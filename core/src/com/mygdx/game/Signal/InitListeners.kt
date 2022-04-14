package com.mygdx.game.Signal

import com.mygdx.game.Areas.MainArea.spawnEngineerItems
import com.mygdx.game.Managers.SignalManager
import com.mygdx.game.Signal.SignalListeners.*

fun initListeners(){
    SignalManager.addSignalListeners(listOf(RemoveObject(),ItemPickedUp(),AbilityGained(),
        UseItems(), ChangeObjectLocation(),MoveObject(),AddGameObject(::spawnEngineerItems), ConversationChange()))
}