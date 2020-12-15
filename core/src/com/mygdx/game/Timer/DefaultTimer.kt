package com.mygdx.game.Timer

import com.mygdx.game.Interfaces.Timer

class DefaultTimer (private val CooldownTime: Float): Timer {
    private var lastUsedTime = 0L
    var coolDownAvailable = true
        private set

    fun UpdateTimer() {
        val currentTime = System.currentTimeMillis()
        val newTime: Float = (currentTime - lastUsedTime).toFloat() / 1000
        if (newTime >= CooldownTime) {
            coolDownAvailable = true
        }
    }
    override fun tryUseCooldown():Boolean{
        if(coolDownAvailable){
            UpdateTimer()
            reset()
            return true
        }
        UpdateTimer()
        return false
    }
    fun reset(){
        lastUsedTime = System.currentTimeMillis()
        coolDownAvailable = false
    }
}