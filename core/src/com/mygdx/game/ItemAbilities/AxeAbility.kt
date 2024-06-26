package com.mygdx.game.ItemAbilities

import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.AbilityId
import com.mygdx.game.AbstractClasses.CharacterAbility
import com.mygdx.game.DefaultTextureHandler
import com.mygdx.game.GameObjects.FrameEvents.AxeSwing
import com.mygdx.game.Timer.DefaultTimer

class AxeAbility() : CharacterAbility() {
    override val abilityId = AbilityId.AXE
    override val triggerKey = com.badlogic.gdx.Input.Keys.Q
    override val texture = DefaultTextureHandler.getTexture("Axe.png")

    override val cooldownTimer = DefaultTimer(2f)

    override fun activeAction() {
        AxeSwing(Vector2(0f,0f),Vector2(0f,0f))
    }
}
