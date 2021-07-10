package com.mygdx.game.FightableEnitityData

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.Interfaces.FightableEntity
import com.mygdx.game.drawHealthBar

class PlayerFightableEntity: FightableEntity {
    override var health = 250f
    override val maxHealth = 250f

    override fun showHealth(sprite: Sprite) {
        drawHealthBar(Vector2(Gdx.graphics.width.toFloat() - 250f, Gdx.graphics.height.toFloat() - 100f), Vector2(200f,50f),this)
    }
}