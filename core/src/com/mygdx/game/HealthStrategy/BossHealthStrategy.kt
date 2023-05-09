package com.mygdx.game.HealthStrategy

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.math.Vector3
import com.mygdx.game.AbstractClasses.Enemy
import com.mygdx.game.Interfaces.HealthStrategy
import com.mygdx.game.camera
import com.mygdx.game.drawHealthBar

class BossHealthStrategy(val enemy: Enemy): HealthStrategy{
    override fun showHealth(sprite: Sprite, health: Float, maxHealth: Float) {
        if(enemy.isAggroed()){
            val pos = Vector3(sprite.x,sprite.y,0f)
            camera.project(pos)
            drawHealthBar(Vector2(pos.x - sprite.width / 4,pos.y + sprite.height - 10f),Vector2(sprite.width * 1.5f,30f),health,maxHealth)
        }
    }
}