package com.mygdx.game.ObjectProperties

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.ParticleEffect
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.Enums.Layer
import com.mygdx.game.GameObjects.MoveableEntities.Projectiles.Icicle
import com.mygdx.game.GameObjects.MoveableEntities.Projectiles.WaterBall
import com.mygdx.game.Interfaces.ObjectProperty
import com.mygdx.game.Interfaces.Renderable
import com.mygdx.game.minus

class Ice(objectOnIce: GameObject) :
    ROJParticleObject(ParticleEffect(), objectOnIce) {
    override val layer = Layer.FOREGROUND

    init {
        val size = objectOnIce.size
        particleEffect.load(Gdx.files.internal("ParticleEmitters/ColdWind.p"), Gdx.files.internal(""))
        particleEffect.emitters.forEach { it.setPosition(objectOnIce.sprite.x, objectOnIce.sprite.y) }
        particleEffect.emitters.forEach { it.setPosition(objectOnIce.sprite.x, objectOnIce.sprite.y) }
        //particleEffect.emitters.first().xOffsetValue.setLow(0f,size.x)
        //particleEffect.emitters.first().yOffsetValue.setLow(- 10f,size.y /2)
        particleEffect.emitters.get(0).xOffsetValue.setLow(0f,size.x)
        particleEffect.emitters.get(0).yOffsetValue.setLow(0f,size.y/2)
        particleEffect.emitters.get(0).life.setHigh(250 * (size.y / 100), 500 * (size.y / 100))
        particleEffect.emitters.get(1).xOffsetValue.setLow(size.x / 2 - 25,size.x / 2 + 25)
        particleEffect.emitters.get(1).yOffsetValue.setLow(size.y)
        particleEffect.emitters.get(1).life.setHigh(3100 * (size.y / 100))
    }
    fun changeWater(waterBall: WaterBall) {
        val location = waterBall.defaultLocation
        location?.removeGameObject(waterBall)
        location?.addGameObject(Icicle(Vector2(waterBall.currentMiddle - Vector2(50f,17f)),
            Vector2(100f,33f),location,waterBall.unitVectorDirection, waterBall.shooter))
    }

}