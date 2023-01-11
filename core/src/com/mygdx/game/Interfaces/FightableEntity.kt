package com.mygdx.game.Interfaces

import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.GameObject

interface FightableEntity {
    var health: Float
    val maxHealth: Float
    val healthStrategy: HealthStrategy
    fun isHit(other: GameObject)
}
interface HealthStrategy {
    fun showHealth(sprite:Sprite, health: Float, maxHealth: Float)
}