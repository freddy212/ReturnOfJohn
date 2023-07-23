package com.mygdx.game.GameObjects.Other

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.Animation.BreakableObjectAnimation
import com.mygdx.game.Collitions.BreakableCollition
import com.mygdx.game.DefaultTextureHandler
import com.mygdx.game.Enums.Layer
import com.mygdx.game.Interfaces.Collition
import com.mygdx.game.Locations.DefaultLocation
import com.mygdx.game.Managers.AnimationManager
import com.mygdx.game.Managers.SignalManager
import com.mygdx.game.SaveHandling.DefaultSaveableObject
import com.mygdx.game.Saving.DefaultSaveStateHandler
import com.mygdx.game.Saving.SaveStateEntity
import com.mygdx.game.Signal.Signals.RemoveObjectSignal

class DefaultBreakableObject(initPosition: Vector2, size: Vector2, defaultLocation: DefaultLocation?) :
    GameObject(initPosition, size, defaultLocation), SaveStateEntity by DefaultSaveStateHandler() {
    override val texture = DefaultTextureHandler.getTexture("FireGate.png")
    override val layer = Layer.ONGROUND
    override val collition = BreakableCollition(this)
    val breakableObjectAnimation = BreakableObjectAnimation(this)
    val sound = Gdx.audio.newSound(Gdx.files.internal("Sound/SoundEffect/Explode.mp3"));

    override fun removeFromLocation() {
        super.removeFromLocation()
        val removeEvents: List<RemoveObjectSignal> = SignalManager.pastSignals.List.filter { it is RemoveObjectSignal }.map { it as RemoveObjectSignal }
        val thisEventOrNull = removeEvents.find { it.entityId == this.entityId }
        if(thisEventOrNull == null){
            sound.play(0.25f)
            AnimationManager.animationManager.add(breakableObjectAnimation)
        }
    }
}