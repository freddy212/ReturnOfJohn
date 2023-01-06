package com.mygdx.game.GameObjects.MoveableEntities

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.g2d.ParticleEffect
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.CharacterAbility
import com.mygdx.game.AbstractClasses.DefaultPositionChange
import com.mygdx.game.Collitions.WaterGunCollition
import com.mygdx.game.DefaultTextureHandler
import com.mygdx.game.Enums.Direction
import com.mygdx.game.Enums.Layer
import com.mygdx.game.Interfaces.DynamicEntity
import com.mygdx.game.Managers.LocationManager
import com.mygdx.game.handleCollitions
import com.mygdx.game.player
/*
class WaterGunSpray(): CharacterAbility() {
    override val triggerKey = Input.Keys.NUM_1
    override val texture = DefaultTextureHandler.getTexture("WaterGun.png")

    var waterEffect: ParticleEffect
    init {
        sprite.setOrigin(0f + size.x / 2,0f)
        polygon.setOrigin(0f + size.x / 2,0f)

        waterEffect = ParticleEffect()
        waterEffect.load(Gdx.files.internal("ParticleEmitters/WaterGunSpray.p"), Gdx.files.internal(""))
        waterEffect.emitters.first().xOffsetValue.setLow(0f,250f)
      //  waterEffect.emitters.first().yOffsetValue.setLow(- 10f,size.y /2)
    }
    override val collition = WaterGunCollition()
    private fun setDirection(direction: Direction){
        val rotation = when(direction){
            Direction.UP -> 0f
            Direction.LEFT -> 90f
            Direction.DOWN -> 180f
            Direction.RIGHT -> 270f
        }
        polygon.rotation = rotation
        sprite.rotation = rotation
        waterEffect.emitters.first().angle.setHigh(-20f + rotation + 90, 20f + rotation + 90)
        waterEffect.emitters.first().angle.setLow(rotation + 90)

        waterEffect.emitters.first().xOffsetValue.setLow(10f)
        waterEffect.emitters.first().yOffsetValue.setLow(0f,0f)

        val offsetValue = 200f

        when(direction){
            Direction.RIGHT -> waterEffect.emitters.first().xOffsetValue.setLow(0f,offsetValue)
            Direction.LEFT -> waterEffect.emitters.first().xOffsetValue.setLow(0f,-offsetValue)
            Direction.UP -> waterEffect.emitters.first().yOffsetValue.setLow(0f,offsetValue)
            Direction.DOWN -> waterEffect.emitters.first().yOffsetValue.setLow(0f,-offsetValue)
        }
    }

    override fun frameTask() {
        super.frameTask()
        setDirection(player.direction)
        setPosition(Vector2(player.sprite.x + ((player.size.x - size.x) / 2), player.sprite.y + player.size.y / 2),this)
        //waterEffect.update(Gdx.graphics.deltaTime)
        handleCollitions(this,polygon,LocationManager.MoveCollitionGameObjects)
    }

    override fun render(batch: PolygonSpriteBatch){
        waterEffect.emitters.first().setPosition(this.sprite.x, this.sprite.y)
        waterEffect.update(Gdx.graphics.deltaTime)
        waterEffect.draw(batch)
    }

}*/