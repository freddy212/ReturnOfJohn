package com.mygdx.game.ObjectProperties

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.ParticleEffect
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.Interfaces.ObjectProperty
import com.mygdx.game.Interfaces.Renderable
import com.mygdx.game.Utils.RenderGraph.Companion.addToSceneGraph

abstract class ROJParticleObject(val particleEffect: ParticleEffect, val objectAttached: GameObject,val posModifier: Vector2 = Vector2(0f,0f)):
    Renderable, ObjectProperty {
    override fun render(batch: PolygonSpriteBatch){
        particleEffect.emitters.forEach {it.setPosition(objectAttached.sprite.x + posModifier.x,objectAttached.sprite.y + posModifier.y) }
        particleEffect.update(Gdx.graphics.deltaTime)
        particleEffect.draw(batch)
    }
    fun start(){
        particleEffect.start()
    }
}