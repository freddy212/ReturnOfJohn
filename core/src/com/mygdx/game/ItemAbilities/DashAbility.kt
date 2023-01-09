package com.mygdx.game.ItemAbilities

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.math.Vector3
import com.mygdx.game.AbstractClasses.AbilityId
import com.mygdx.game.AbstractClasses.CharacterAbility
import com.mygdx.game.DefaultTextureHandler
import com.mygdx.game.Enums.CharacterState
import com.mygdx.game.Events.ActionAfterFramesEvent
import com.mygdx.game.Events.ActionBeforeFramesEvent
import com.mygdx.game.Managers.EventManager
import com.mygdx.game.ObjectProperties.FireDashEffect
import com.mygdx.game.Timer.DefaultTimer
import com.mygdx.game.camera
import com.mygdx.game.getUnitVectorTowardsPoint
import com.mygdx.game.player

open class DashAbility: CharacterAbility() {
    override val abilityId = AbilityId.DASH
    override val triggerKey = Input.Keys.NUM_3
    override val texture = DefaultTextureHandler.getTexture("fireball.png")
    override val cooldownTimer = DefaultTimer(2f)
    val fireDashEffect = FireDashEffect()
    val dashFrames = 20f

    val sound = Gdx.audio.newSound(Gdx.files.internal("Sound/SoundEffect/fireworks.mp3"));

    override fun activeAction() {
        player.setCurrentSpeed(player.getCurrentSpeed() * 3.0f)
        player.characterState = CharacterState.DASHING
        val actionBeforeFramesEvent = ActionBeforeFramesEvent(dashFrames, ::inDashAction)
        val actionAfterFramesEvent = ActionAfterFramesEvent(dashFrames,::afterDashAction)
        EventManager.eventManager.add(actionBeforeFramesEvent)
        EventManager.eventManager.add(actionAfterFramesEvent)
        player.properties.add(fireDashEffect)
        fireDashEffect.particleEffect.reset()
        fireDashEffect.start()
        sound.play(0.25f)
    }

   fun inDashAction(){
        if(player.characterState == CharacterState.DASHING) {
            val clickPosition = camera.unproject(Vector3(Gdx.input.x.toFloat(), Gdx.input.y.toFloat(), 0f))
            val unitVectorTowardsPoint = getUnitVectorTowardsPoint(
                Vector2(player.sprite.x, player.sprite.y),
                Vector2(clickPosition.x, clickPosition.y)
            )
            player.move((unitVectorTowardsPoint))
            player.setCurrentSpeed(player.getCurrentSpeed() - 0.8f)
        }
    }
    fun afterDashAction(){
        if(player.characterState == CharacterState.DASHING){
            player.setCurrentSpeed(player.baseSpeed)
            player.characterState = CharacterState.FREE
        }
        player.properties.remove(fireDashEffect)
    }
}