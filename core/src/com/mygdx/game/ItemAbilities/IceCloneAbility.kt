package com.mygdx.game.ItemAbilities

import com.badlogic.gdx.math.Vector2
import com.mygdx.game.*
import com.mygdx.game.AbstractClasses.AbilityId
import com.mygdx.game.AbstractClasses.CharacterAbility
import com.mygdx.game.GameObjects.MoveableEntities.IceClone
import com.mygdx.game.Managers.AreaManager
import com.mygdx.game.Managers.LocationManager
import com.mygdx.game.Timer.DefaultTimer

open class IceCloneAbility(): CharacterAbility(){
    override val abilityId = AbilityId.ICECLONE
    override val triggerKey = com.badlogic.gdx.Input.Keys.NUM_4
    override val texture = DefaultTextureHandler.getTexture("player.png")

    override val cooldownTimer = DefaultTimer(1f)
    val iceClone = IceClone(
        Vector2(-100000f, -100000f),
        Vector2(player.width * 1.2f, player.height * 1.2f),
        null
    )

    override fun activeAction() {
        iceClone.removeFromLocation()
        iceClone.addToLocation(player.defaultLocation!!)
        iceClone.setPosition(player.currentMiddle - Vector2(iceClone.width / 2, iceClone.height / 2))
        iceClone.move(Vector2(0f,0f))
    }
}