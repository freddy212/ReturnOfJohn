package com.mygdx.game.Timer

class Timer (private val CooldownTime: Float){
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
    fun tryUseCooldown() :Boolean{
        UpdateTimer()
        if(coolDownAvailable){
            reset()
            return true
        }
        return false
    }
    fun reset(){
        lastUsedTime = System.currentTimeMillis()
        coolDownAvailable = false
    }
}