package com.mygdx.game.ObjectProperties

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.ParticleEffect
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.Enums.Layer
import com.mygdx.game.GameObjects.Other.DefaultBreakableObject
import com.mygdx.game.middleOfObject
import com.mygdx.game.minus

class BreakableEffect(
    objectAttached: DefaultBreakableObject,
    posModifier: Vector2 = Vector2(0f, 0f),
): ROJParticleObject(ParticleEffect(), objectAttached, objectAttached.currentMiddle - objectAttached.bottomleft) {
    override val layer = objectAttached.layer

    init {
        particleEffect.load(Gdx.files.internal("ParticleEmitters/BreakObject.p"), Gdx.files.internal(""))
    }
}