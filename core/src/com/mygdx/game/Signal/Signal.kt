package com.mygdx.game.Signal

import com.mygdx.game.Signal.Signals.SIGNALTYPE
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Serializable
open class Signal(val signaltype: SIGNALTYPE) {

    open fun encode():String{
        return Json.encodeToString(this)
    }
}