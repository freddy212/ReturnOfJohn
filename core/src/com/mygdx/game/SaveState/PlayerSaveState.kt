package com.mygdx.game.SaveState

import com.mygdx.game.Interfaces.AreaIdentifier
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Serializable
class PlayerSaveState(val playerXPos: Float, val playerYPos: Float, val areaIdentifier: AreaIdentifier) {
    fun encode(): String{
        return Json.encodeToString(this)
    }
}