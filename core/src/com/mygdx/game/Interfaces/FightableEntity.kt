package com.mygdx.game.Interfaces

import com.badlogic.gdx.graphics.g2d.Sprite

interface FightableEntity {
    var health: Float
    val maxHealth: Float
    val healthStrategy: HealthStrategy
}
interface HealthStrategy {
    fun showHealth(sprite:Sprite, health: Float, maxHealth: Float)
}