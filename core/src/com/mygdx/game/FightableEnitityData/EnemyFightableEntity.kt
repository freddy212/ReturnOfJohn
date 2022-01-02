package com.mygdx.game.FightableEnitityData

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.math.Vector3
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.Interfaces.FightableEntity
import com.mygdx.game.Interfaces.HealthStrategy
import com.mygdx.game.camera
import com.mygdx.game.drawHealthBar

class EnemyHealthStrategy(): HealthStrategy{

    override fun showHealth(sprite:Sprite, health: Float, maxHealth: Float) {
        val pos = Vector3(sprite.x,sprite.y,0f)
        camera.project(pos)
        drawHealthBar(Vector2(pos.x,pos.y + sprite.height - 10f),Vector2(sprite.width,10f),health,maxHealth)
    }
}