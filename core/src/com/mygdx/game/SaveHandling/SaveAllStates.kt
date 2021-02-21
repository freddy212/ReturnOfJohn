package com.mygdx.game.SaveHandling

import com.mygdx.game.Managers.LocationManager
import com.mygdx.game.SaveState.PlayerSaveState
import com.mygdx.game.player
import com.mygdx.game.playerSaveState

fun savePlayerStates(){
    playerSaveState.update()
    FileHandler.writeToFile(playerSaveState.entityId,playerSaveState.encode())
}