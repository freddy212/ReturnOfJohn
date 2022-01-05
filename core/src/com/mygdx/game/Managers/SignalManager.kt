package com.mygdx.game.Managers

import com.mygdx.game.Interfaces.Event
import com.mygdx.game.SaveHandling.FileHandler.Companion.writeSignalToFile
import com.mygdx.game.Signal.SIGNALTYPE
import com.mygdx.game.Signal.Signal
import com.mygdx.game.Signal.SignaledEventListener
import com.mygdx.game.Utils.ResourceList

class SignalManager {
        companion object {
            val listenerMap = mutableMapOf<SIGNALTYPE,MutableList<SignaledEventListener>>()
            val signalManager = ResourceList<Signal>()
            init {
                for (signalType in SIGNALTYPE.values()){
                    listenerMap[signalType] = mutableListOf()
                }
            }
            fun addSignalListeners(listeners: List<SignaledEventListener>){
                listeners.forEach {
                    listenerMap[it.signaltype]!!.add(it)
                }
            }
            fun emitSignal(signal: Signal, saveSignal: Boolean = true){
                if(saveSignal){
                    writeSignalToFile(signal)
                }
                signalManager.add(signal)
            }
            fun useSignals(){
                for(signal in signalManager.List){
                    listenerMap[signal.signaltype]!!.forEach {
                        it.triggerEvent(signal)
                    }
                    signalManager.remove(signal)
                }
            }
        }
}