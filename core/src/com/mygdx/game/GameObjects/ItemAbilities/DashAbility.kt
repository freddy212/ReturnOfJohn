package com.mygdx.game.GameObjects.ItemAbilities

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.math.Vector3
import com.mygdx.game.AbstractClasses.AbilityId
import com.mygdx.game.AbstractClasses.CharacterAbility
import com.mygdx.game.DefaultTextureHandler
import com.mygdx.game.Enums.CharacterState
import com.mygdx.game.Events.ActionAfterFramesEvent
import com.mygdx.game.Events.ActionBeforeFramesEvent
import com.mygdx.game.Interfaces.Timer
import com.mygdx.game.Managers.EventManager
import com.mygdx.game.ObjectProperties.FireDashEffect
import com.mygdx.game.Timer.DefaultTimer
import com.mygdx.game.camera
import com.mygdx.game.getUnitVectorTowardsPoint
import com.mygdx.game.player
import kotlin.properties.Delegates

class DashAbility: CharacterAbility() {
    override val abilityId = AbilityId.DASH
    override val triggerKey = Input.Keys.NUM_5
    override val texture = DefaultTextureHandler.getTexture("fireball.png")
    override val cooldownTimer = DefaultTimer(2f)
    val fireDashEffect = FireDashEffect()

    override fun activeAction() {
        player.setCurrentSpeed(player.getCurrentSpeed() * 3.0f)
        player.characterState = CharacterState.DASHING
        val actionBeforeFramesEvent = ActionBeforeFramesEvent(20f, ::movePlayerTowardsCursor)
        val actionAfterFramesEvent = ActionAfterFramesEvent(20f,::afterDashAction)
        EventManager.eventManager.add(actionBeforeFramesEvent)
        EventManager.eventManager.add(actionAfterFramesEvent)
        player.properties.add(fireDashEffect)
        fireDashEffect.particleEffect.reset()
        fireDashEffect.start()
    }

    fun movePlayerTowardsCursor(){
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