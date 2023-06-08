package com.mygdx.game.ObjectProperties

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.ParticleEffect
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.Enums.Layer
import com.mygdx.game.player

class FireDashEffect(objectAttached: GameObject = player, posModifier: Vector2 = Vector2(objectAttached.sprite.width/2, objectAttached.sprite.height/2)): ROJParticleObject(ParticleEffect(), objectAttached, posModifier) {
    override val layer = Layer.FOREGROUND

    init {
        particleEffect.load(Gdx.files.internal("ParticleEmitters/Dash.p"), Gdx.files.internal(""))
    }
}