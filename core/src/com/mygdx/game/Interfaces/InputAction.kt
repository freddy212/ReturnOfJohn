package com.mygdx.game.Interfaces

interface InputAction {
    val keycodes: List<Int>
    fun action(): Unit
    fun inactiveAction() {}
}