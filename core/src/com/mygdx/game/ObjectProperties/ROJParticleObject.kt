package com.mygdx.game.ObjectProperties

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.ParticleEffect
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.Interfaces.ObjectProperty
import com.mygdx.game.Interfaces.Renderable
import com.mygdx.game.RenderGraph.Companion.addToSceneGraph

abstract class ROJParticleObject(val particleEffect: ParticleEffect, val objectAttached: GameObject): Renderable,
    ObjectProperty {
    override fun render(batch: PolygonSpriteBatch){
        particleEffect.emitters.forEach {it.setPosition(objectAttached.sprite.x,objectAttached.sprite.y) }
        particleEffect.update(Gdx.graphics.deltaTime)
        particleEffect.draw(batch)
    }
    override fun frameTask() {
        addToSceneGraph(this)
    }
}