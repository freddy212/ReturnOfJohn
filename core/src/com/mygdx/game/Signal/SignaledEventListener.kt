package com.mygdx.game.Signal

interface SignaledEventListener {
    val signaltype: SIGNALTYPE
    fun triggerEvent(signal:Signal)
}