package com.mygdx.game.Saving

import com.mygdx.game.Interfaces.AreaIdentifier
import com.mygdx.game.Managers.AreaManager
import com.mygdx.game.SaveHandling.SaveableObject
import com.mygdx.game.player
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Serializable
class PlayerSaveState(var playerXPos: Float, var playerYPos: Float,
                      var areaIdentifier: AreaIdentifier,override val entityId:Int):SaveableObject(){

    fun update(){
        playerXPos = player.sprite.x
        playerYPos = player.sprite.y
        areaIdentifier = AreaManager.activeArea.identifier
    }
    fun encode(): String{
        return Json.encodeToString(this)
    }
}