package com.mygdx.game.ObjectProperties

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.ParticleEffect
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.AbstractClasses.Generator
import com.mygdx.game.Animation.GeneratorAnimation
import com.mygdx.game.Enums.Direction
import com.mygdx.game.Enums.Layer
import com.mygdx.game.Enums.getDirectionFromUnitVector
import com.mygdx.game.player
import kotlin.math.max
import kotlin.math.min

class GeneratorEffect(val generator: Generator, posOffset: Vector2,val duration: Float,val animation: GeneratorAnimation): ROJParticleObject(
    ParticleEffect(), generator, posOffset) {
    override val layer = Layer.FOREGROUND
    val maxWidth = generator.width
    val maxHeight = generator.height
    val decrementValueWidth = ( duration / maxWidth)
    val decrementValueHeight = ( duration / maxHeight)

    init {
        particleEffect.load(Gdx.files.internal("ParticleEmitters/GeneratorEffect.p"), Gdx.files.internal(""))
        val direction = getDirectionFromUnitVector(generator.unitVectorDirection)
        if(direction == Direction.DOWN || direction == Direction.UP){
            particleEffect.emitters.first().xOffsetValue.setLow(0f,generator.width)
        }
        else{
            particleEffect.emitters.first().yOffsetValue.setLow(0f,generator.height)
        }

    }

    override fun render(batch: PolygonSpriteBatch) {
        val direction = getDirectionFromUnitVector(generator.unitVectorDirection)
        if(direction == Direction.DOWN || direction == Direction.UP){
            val decrementValue = min(maxWidth / 3f, decrementValueWidth * animation.currentFrame)
            particleEffect.emitters.first().xOffsetValue.setLow(0f + decrementValue,maxWidth - decrementValue)
        }
        else{
            val decrementValue = min(maxHeight / 3f, decrementValueHeight * animation.currentFrame)
            particleEffect.emitters.first().yOffsetValue.setLow(0f + decrementValue, maxHeight - decrementValue)
        }
        super.render(batch)
    }
}