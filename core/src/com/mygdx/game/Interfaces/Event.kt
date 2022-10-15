package com.mygdx.game.Interfaces

interface Event {
    fun execute()
    fun runOnce(): Boolean = false
}