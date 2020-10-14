package com.mygdx.game.Timer

class Timer (private val CooldownTime: Float){
    private var time = 0L
    var coolDownAvailable = true
        private set

    fun UpdateTimer() {
        val currentTime = System.currentTimeMillis()
        val newTime: Float = (currentTime - time).toFloat() / 1000
        if (newTime >= CooldownTime) {
            coolDownAvailable = true
        }
    }
    fun tryUseCooldown() :Boolean{
        UpdateTimer()
        if(coolDownAvailable){
            time = System.currentTimeMillis()
            coolDownAvailable = false
            return true
        }
        return false
    }
}