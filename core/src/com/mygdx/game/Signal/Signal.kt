package com.mygdx.game.Signal

import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

enum class SIGNALTYPE{ITEM_PICKED_UP,REMOVE_OBJECT, ABILITY_GAINED, USE_ITEMS }

@Serializable
open class Signal(open val signaltype: SIGNALTYPE, open val id: Int, open val amount: Int = 0) {
    open fun encode():String{
        return Json.encodeToString(this)
    }
}