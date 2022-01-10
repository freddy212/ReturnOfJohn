package com.mygdx.game.Interfaces

interface Timer {
    fun tryUseCooldown():Boolean
    fun cooldownAvailable(): Boolean
}
