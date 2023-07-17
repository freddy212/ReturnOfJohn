package com.mygdx.game.Interfaces

import com.mygdx.game.GameObjects.Buttons.IceButton

interface ButtonGate {
    fun buttonPressed()
    fun buttonReleased()
    val buttons: MutableList<IceButton>
}