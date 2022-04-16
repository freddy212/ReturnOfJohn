package com.mygdx.game.Interfaces

import com.badlogic.gdx.graphics.g2d.Sprite
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.HitOppositeDirection

interface FightableEntity {
    var health: Float
    val maxHealth: Float
    val healthStrategy: HealthStrategy
    fun HitAction(other: GameObject, thisEntity: FightableEntity) = HitOppositeDirection(other, thisEntity)
}
interface HealthStrategy {
    fun showHealth(sprite:Sprite, health: Float, maxHealth: Float)
}