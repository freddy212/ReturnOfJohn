package com.mygdx.game.Trimer

import com.mygdx.game.Timer.DefaultTimer

class DelayTimer (timeUntilExecute: Float){
    private val timer = DefaultTimer(timeUntilExecute)
    init {
        timer.tryUseCooldown()
    }
    private var timePassed = false

    fun getTimeHasPassed():Boolean{
        if(!timePassed && timer.tryUseCooldown()){
            timePassed = true
        }
        return timePassed
    }
    fun resetDelay(){
        timer.reset()
        timePassed = false
    }
}