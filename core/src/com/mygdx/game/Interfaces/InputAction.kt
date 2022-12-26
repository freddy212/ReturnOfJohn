package com.mygdx.game.Interfaces

interface InputAction {
    val keycodes: List<Int>
    fun action(keycode: Int): Unit
    fun inactiveAction() {}
}