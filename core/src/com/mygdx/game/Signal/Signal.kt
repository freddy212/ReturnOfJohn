package com.mygdx.game.Signal

import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

enum class SIGNALTYPE{ITEM_PICKED_UP,REMOVE_OBJECT, ABILITY_GAINED, USE_ITEMS, CHANGE_OBJECT_LOCATION, MOVE_OBJECT_COORDINATES, ADD_OBJECT, CHANGE_CONVERSATION, BUTTON_ACCEPTED}

@Serializable
open class Signal(val signaltype: SIGNALTYPE, val id: Int, val amount: Int = 0, val x: Float = 0f,
                  val y: Float = 0f, val stringValue:String = "", val areaIdentifier: Int = 0, val addMethod: Int = 0) {
    open fun encode():String{
        return Json.encodeToString(this)
    }
}