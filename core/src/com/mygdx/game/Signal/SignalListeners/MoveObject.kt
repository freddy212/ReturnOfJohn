package com.mygdx.game.Signal.SignalListeners

import com.badlogic.gdx.math.Vector2
import com.mygdx.game.SetFixedPosition
import com.mygdx.game.Signal.Signal
import com.mygdx.game.Signal.SignaledEventListener
import com.mygdx.game.Signal.Signals.MoveObjectSignal
import com.mygdx.game.Signal.Signals.SIGNALTYPE
import com.mygdx.game.getGameObjectWithEntityId

class MoveObject: SignaledEventListener {
    override val signaltype = SIGNALTYPE.MOVE_OBJECT

    override fun triggerEvent(signal: Signal) {
        val moveObjectSignal = signal as MoveObjectSignal
        val vector = Vector2(moveObjectSignal.x, moveObjectSignal.y)
        val gameObject =  getGameObjectWithEntityId(signal.entityId)
        gameObject!!.setPosition(vector)
    }
}