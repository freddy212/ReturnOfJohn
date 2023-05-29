package com.mygdx.game.ItemAbilities

import com.badlogic.gdx.math.Vector2
import com.mygdx.game.*
import com.mygdx.game.AbstractClasses.AbilityId
import com.mygdx.game.AbstractClasses.CharacterAbility
import com.mygdx.game.AbstractClasses.ToggleAbility
import com.mygdx.game.GameObjects.MoveableEntities.Projectiles.Icicle
import com.mygdx.game.Managers.LocationManager
import com.mygdx.game.Timer.DefaultTimer

class IcicleAbility(): ToggleAbility() {
    override val abilityId = AbilityId.ICICLE
    override val triggerKey = com.badlogic.gdx.Input.Keys.NUM_4
    override val texture = DefaultTextureHandler.getTexture("Icicle.png")
    override val cooldownTimer = DefaultTimer(1f)

    override fun activeAction(){
        val icicle = Icicle(player.currentMiddle - Vector2(50f,17f) + (player.unitVectorDirection * 100f),Vector2(100f,33f),LocationManager.newDefaultLocation, player.unitVectorDirection, player)
        icicle.defaultLocation!!.addGameObject(icicle)
    }
}