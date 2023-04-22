package com.mygdx.game.ObjectProperties

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.ParticleEffect
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.AbstractClasses.MoveableObject
import com.mygdx.game.DefaultSoundHandler.music
import com.mygdx.game.DefaultTextureHandler
import com.mygdx.game.Enums.Layer
import com.mygdx.game.GameObjects.MoveableEntities.Characters.Player
import com.mygdx.game.Interfaces.DefaultAreaEntranceCollition
import com.mygdx.game.Interfaces.ObjectProperty
import com.mygdx.game.distance
import com.mygdx.game.player

enum class SoundEffectWhenEntered {FIRE, NONE}

abstract class ROJParticleObject(val particleEffect: ParticleEffect, val objectAttached: GameObject,val posModifier: Vector2 = Vector2(0f,0f)):
    GameObject(objectAttached.initPosition, objectAttached.size, objectAttached.defaultLocation), ObjectProperty {
    override val layer: Layer = Layer.BEFORELOCATION
    override val texture = DefaultTextureHandler.getTexture("sensor.png")
    override val collition = ParticleAreaCollition(objectAttached, this)
    abstract val soundEffectWhenEntered: SoundEffectWhenEntered

    init {
        this.polygon.setScale(4.0f,4.0f)
    }
    override fun render(batch: PolygonSpriteBatch){
        particleEffect.emitters.forEach {it.setPosition(objectAttached.sprite.x + posModifier.x,objectAttached.sprite.y + posModifier.y) }
        particleEffect.update(Gdx.graphics.deltaTime)
        particleEffect.draw(batch)
    }
    fun start(){
        particleEffect.start()
    }
}

class ParticleAreaCollition(val objectAttached: GameObject,val  royROJParticleObject: ROJParticleObject): DefaultAreaEntranceCollition(){
    override fun collitionHappened(collidedObject: GameObject) {
        if(collidedObject is Player){
            super.collitionHappened(collidedObject)
        }
    }
    override fun movedInsideAction(objectEntered: GameObject) {
        if(royROJParticleObject.soundEffectWhenEntered == SoundEffectWhenEntered.FIRE){
            val distance = distance(player.currentPosition(), objectAttached.currentPosition())
            music.volume = 2f
            music.play()
            println("music start")
        }
    }

    override fun movedOutsideAction(objectLeaved: GameObject) {
        if(objectLeaved == player) {
            println("music stop")
            music.stop()
        }
    }

}