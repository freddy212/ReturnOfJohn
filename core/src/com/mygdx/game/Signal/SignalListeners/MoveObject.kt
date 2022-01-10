package com.mygdx.game.Signal.SignalListeners

import com.badlogic.gdx.math.Vector2
import com.mygdx.game.SetFixedPosition
import com.mygdx.game.Signal.SIGNALTYPE
import com.mygdx.game.Signal.Signal
import com.mygdx.game.Signal.SignaledEventListener
import com.mygdx.game.getGameObjectWithEntityId

class MoveObject: SignaledEventListener {
    override val signaltype = SIGNALTYPE.MOVE_OBJECT_COORDINATES

    override fun triggerEvent(signal: Signal) {
        val vector = Vector2(signal.x,signal.y)
        val gameObject =  getGameObjectWithEntityId(signal.id)
        gameObject!!.setPosition(vector)
    }
}