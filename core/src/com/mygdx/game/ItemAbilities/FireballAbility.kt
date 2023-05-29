package com.mygdx.game.ItemAbilities

import com.badlogic.gdx.math.Vector2
import com.mygdx.game.*
import com.mygdx.game.AbstractClasses.AbilityId
import com.mygdx.game.AbstractClasses.CharacterAbility
import com.mygdx.game.AbstractClasses.ToggleAbility
import com.mygdx.game.GameObjects.MoveableEntities.Projectiles.Fireball
import com.mygdx.game.Managers.LocationManager
import com.mygdx.game.Timer.DefaultTimer

class FireballAbility: ToggleAbility() {
    override val abilityId = AbilityId.FIREBALL
    override val triggerKey = com.badlogic.gdx.Input.Keys.NUM_1
    override val texture = DefaultTextureHandler.getTexture("fireball.png")
    override val cooldownTimer = DefaultTimer(1f)

    override fun activeAction(){
        val fireball = Fireball(
            player.currentMiddle - Vector2(50f,17f) + (player.unitVectorDirection * 100f),
            Vector2(100f,33f),
            LocationManager.newDefaultLocation, player.unitVectorDirection, player)
        fireball.defaultLocation!!.addGameObject(fireball)
    }
}