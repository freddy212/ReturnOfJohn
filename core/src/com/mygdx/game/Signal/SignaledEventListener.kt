package com.mygdx.game.Signal

import com.mygdx.game.Signal.Signals.SIGNALTYPE

interface SignaledEventListener {
    val signaltype: SIGNALTYPE
    fun triggerEvent(signal:Signal)
}