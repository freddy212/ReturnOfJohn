package com.mygdx.game.ObjectProperties

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.ParticleEffect
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.Enums.Layer
import com.mygdx.game.Events.DefaultEvent
import com.mygdx.game.Interfaces.Event

class Fire(objectOnFire: GameObject, val extinguishFireEvent: Event = DefaultEvent()) : ROJParticleObject(ParticleEffect(),objectOnFire) {
    override val layer = Layer.FOREGROUND

    init {
        particleEffect.load(Gdx.files.internal("ParticleEmitters/Fire.p"), Gdx.files.internal(""))
        particleEffect.emitters.first().xOffsetValue.setLow(0f,objectOnFire.size.x)
        particleEffect.emitters.first().yOffsetValue.setLow(- 10f,objectOnFire.size.y /2)

        particleEffect.start()
}
}