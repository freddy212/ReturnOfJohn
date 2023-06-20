package com.mygdx.game.Animation

import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch
import com.mygdx.game.Enums.Layer
import com.mygdx.game.ObjectProperties.ROJParticleObject

abstract class ParticleAnimation: DefaultAnimation() {

    abstract val animationEffect: ROJParticleObject

    override fun render(batch: PolygonSpriteBatch) {
        if(currentFrame == 1){
            animationEffect.start()
        }
        animationEffect.render(batch)
    }

    override fun reset() {
        super.reset()
        animationEffect.particleEffect.reset()
    }
}