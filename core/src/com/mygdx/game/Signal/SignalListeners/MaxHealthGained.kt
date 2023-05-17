package com.mygdx.game.Signal.SignalListeners

import com.mygdx.game.Signal.Signal
import com.mygdx.game.Signal.SignaledEventListener
import com.mygdx.game.Signal.Signals.MaxHealthGainedSignal
import com.mygdx.game.Signal.Signals.SIGNALTYPE
import com.mygdx.game.player

class MaxHealthGained: SignaledEventListener {
    override val signaltype = SIGNALTYPE.MAX_HEALTH_GAINED

    override fun triggerEvent(signal: Signal) {
        val maxHealthGainedSignal = signal as MaxHealthGainedSignal

        player.maxHealth += maxHealthGainedSignal.health
        player.health = player.maxHealth
    }
}