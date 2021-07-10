package com.mygdx.game.FightableEnitityData

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.math.Vector3
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.Interfaces.FightableEntity
import com.mygdx.game.camera
import com.mygdx.game.drawHealthBar

class EnemyFightableEntity(): FightableEntity {
    override var health = 100f
    override val maxHealth = 100f

    override fun showHealth(sprite:Sprite) {
        val pos = Vector3(sprite.x,sprite.y,0f)
        camera.project(pos)
        drawHealthBar(Vector2(pos.x,pos.y + sprite.height - 10f),Vector2(sprite.width,10f),this)
    }
}