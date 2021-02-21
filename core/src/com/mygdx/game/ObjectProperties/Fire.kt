package com.mygdx.game.ObjectProperties

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.ParticleEffect
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.Enums.Layer
import com.mygdx.game.Interfaces.Event

class Fire(val position:Vector2, val size: Vector2,val extinguishFireEvent: Event, objectOnFire: GameObject) : ROJParticleObject(ParticleEffect(),objectOnFire) {
    override val layer = Layer.FOREGROUND
    init {
        particleEffect.load(Gdx.files.internal("ParticleEmitters/Fire.p"), Gdx.files.internal(""))
        particleEffect.emitters.first().setPosition(position.x, position.y)
        particleEffect.emitters.first().xOffsetValue.setLow(0f,size.x)
        particleEffect.emitters.first().yOffsetValue.setLow(- 10f,size.y /2)

        particleEffect.start()
}
    fun fireExtinguised(){
        extinguishFireEvent.execute()
        objectAttached.properties.remove(this)
    }
}