package com.mygdx.game.Signal

interface SignaledEventListener {
    abstract val signaltype: SIGNALTYPE
    fun triggerEvent(signal:Signal)
}