package com.mygdx.game.Signal

import com.mygdx.game.Managers.SignalManager
import com.mygdx.game.Signal.SignalListeners.*
import com.mygdx.game.spawnDoorOne
import com.mygdx.game.spawnDoorTwo
import com.mygdx.game.spawnEngineerItems

fun initListeners(){
    SignalManager.addSignalListeners(listOf(RemoveObject(),ItemPickedUp(),AbilityGained(),
        UseItems(), ChangeObjectLocation(),MoveObject(),AddGameObject(::spawnEngineerItems,ADDMETHODS.ENGINEER), ConversationChange(),
        AddGameObject(::spawnDoorOne,ADDMETHODS.DOOR1),AddGameObject(::spawnDoorTwo,ADDMETHODS.DOOR2), ButtonAccepted()))
}