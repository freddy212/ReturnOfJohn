package com.mygdx.game.HealthStrategy

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.Interfaces.HealthStrategy
import com.mygdx.game.drawHealthBar

class PlayerHealthStrategy: HealthStrategy {
    override fun showHealth(sprite: Sprite, health: Float, maxHealth: Float) {
        drawHealthBar(Vector2(Gdx.graphics.width.toFloat() - 250f, Gdx.graphics.height.toFloat() - 100f), Vector2(200f,50f),health,maxHealth)
    }
}