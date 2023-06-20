package com.mygdx.game.Animation

import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.AbstractClasses.Generator
import com.mygdx.game.Enums.Layer
import com.mygdx.game.ObjectProperties.GeneratorEffect
import com.mygdx.game.ObjectProperties.ROJParticleObject

class GeneratorAnimation(override val animationAction: () -> Unit, attachedObject: Generator, posOffset: Vector2) : ParticleAnimation(){

    override val duration: Int = 90
    override var actionFrame: Int = 60
    override val layer: Layer = Layer.AIR
    override val animationEffect = GeneratorEffect(attachedObject, posOffset, duration.toFloat(), this)
}