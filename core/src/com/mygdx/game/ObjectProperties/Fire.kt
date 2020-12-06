package com.mygdx.game.ObjectProperties

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.ParticleEffect
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.Collitions.DoorCollition
import com.mygdx.game.Collitions.IllegalMoveCollition
import com.mygdx.game.Collitions.ToggleCollition
import com.mygdx.game.Enums.Layer
import com.mygdx.game.Interfaces.Collition
import com.mygdx.game.Interfaces.Event
import com.mygdx.game.Interfaces.ObjectProperty
import com.mygdx.game.Interfaces.Renderable
import com.mygdx.game.RenderGraph.Companion.addToSceneGraph
import com.mygdx.game.middleOfObject

class Fire(val position:Vector2, val size: Vector2,val extinguishFireEvent: Event, val objectOnFire: GameObject) : ObjectProperty,Renderable {
    override val layer = Layer.FOREGROUND
    lateinit var fireEffect: ParticleEffect

    init {
        fireEffect = ParticleEffect()
        fireEffect.load(Gdx.files.internal("ParticleEmitters/Fire.p"), Gdx.files.internal(""))
        fireEffect.emitters.first().setPosition(position.x, position.y)
        fireEffect.emitters.first().xOffsetValue.setLow(0f,size.x)
        fireEffect.emitters.first().yOffsetValue.setLow(- 10f,size.y /2)

        fireEffect.start()
}
    override fun render(batch: PolygonSpriteBatch) {
        fireEffect.emitters.first().setPosition(objectOnFire.sprite.x,objectOnFire.sprite.y)
        fireEffect.update(Gdx.graphics.deltaTime)
        fireEffect.draw(batch)
    }

    override fun frameTask() {
        addToSceneGraph(this)
    }
    fun fireExtinguised(){
        extinguishFireEvent.execute()
        objectOnFire.properties.remove(this)
    }
}