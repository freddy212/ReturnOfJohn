package com.mygdx.game.SaveState

import com.mygdx.game.Interfaces.AreaIdentifier
import com.mygdx.game.Managers.LocationManager
import com.mygdx.game.SaveHandling.SaveableObject
import com.mygdx.game.player
import kotlinx.serialization.PolymorphicSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Serializable
class PlayerSaveState(var playerXPos: Float, var playerYPos: Float,
                      var areaIdentifier: AreaIdentifier,override val entityId:Int):SaveableObject(){

    fun update(){
        playerXPos = player.sprite.x
        playerYPos = player.sprite.y
        areaIdentifier = LocationManager.activeArea.identifier
    }
    fun encode(): String{
        return Json.encodeToString(this)
    }
}