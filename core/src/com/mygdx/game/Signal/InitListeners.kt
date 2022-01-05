package com.mygdx.game.Signal

import com.mygdx.game.Managers.SignalManager
import com.mygdx.game.Signal.SignalListeners.AbilityGained
import com.mygdx.game.Signal.SignalListeners.ItemPickedUp
import com.mygdx.game.Signal.SignalListeners.RemoveObject
import com.mygdx.game.Signal.SignalListeners.UseItems

fun initListeners(){
    SignalManager.addSignalListeners(listOf(RemoveObject(),ItemPickedUp(),AbilityGained(), UseItems()))
}