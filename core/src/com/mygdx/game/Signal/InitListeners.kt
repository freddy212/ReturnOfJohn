package com.mygdx.game.Signal

import com.mygdx.game.*
import com.mygdx.game.Managers.SignalManager
import com.mygdx.game.Signal.SignalListeners.*

fun initListeners() {
    SignalManager.addSignalListeners(
        listOf(
            RemoveObject(),
            ItemPickedUp(),
            AbilityGained(),
            UseItems(),
            ChangeObjectLocation(),
            MoveObject(),
            ConversationChange(),
            AddGameObject(::spawnEngineerItems, ADDMETHODS.ENGINEER),
            AddGameObject(::spawnDoorOne, ADDMETHODS.DOOR1),
            AddGameObject(::spawnDoorTwo, ADDMETHODS.DOOR2),
            AddGameObject(::spawnFrostFireFireLandsDoor, ADDMETHODS.FROSTFIRETOFIRELANDSDOOR),
            AddGameObject(::spawnFireLandsFrostFireDoor, ADDMETHODS.FIRELANDSTOFROSTFIREDOOR),
            AddGameObject(::spawnFirelandsWastelandDoor, ADDMETHODS.FIRELANDSWASTELANDDOOR),
            AddGameObject(::spawnWastelandFirelandsDoor, ADDMETHODS.WASTELANDFIRELANDSDOOR),
            AddGameObject(::spawnIceLandsFrostFireDoor, ADDMETHODS.ICELANDSFROSTFIREDOOR),
            AddGameObject(::spawnFrostFireIceLandsDoor, ADDMETHODS.FROSTFIREICELANDSDOOR),
            ButtonAccepted(),
            MaxHealthGained(),
            AddAbilityItem(),
        )
    )
}