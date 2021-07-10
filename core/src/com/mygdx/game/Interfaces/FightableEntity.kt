package com.mygdx.game.Interfaces

import com.badlogic.gdx.graphics.g2d.Sprite

interface FightableEntity {
    var health: Float
    val maxHealth: Float
    fun showHealth(sprite:Sprite)
}
