package com.mygdx.game.SaveHandling

import com.mygdx.game.Saving.FileHandler
import com.mygdx.game.playerSaveState

fun savePlayerStates(){
    playerSaveState.update()
    FileHandler.writeToFile(playerSaveState.entityId,playerSaveState.encode())
}