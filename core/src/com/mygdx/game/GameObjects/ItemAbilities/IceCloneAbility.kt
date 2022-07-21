package com.mygdx.game.GameObjects.ItemAbilities

import com.badlogic.gdx.math.Vector2
import com.mygdx.game.*
import com.mygdx.game.AbstractClasses.AbilityId
import com.mygdx.game.AbstractClasses.CharacterAbility
import com.mygdx.game.AbstractClasses.DefaultPositionChange
import com.mygdx.game.Enums.Layer
import com.mygdx.game.GameObjects.IceClone
import com.mygdx.game.GameObjects.MoveableEntities.Projectiles.Icicle
import com.mygdx.game.Interfaces.DynamicEntity
import com.mygdx.game.Managers.AreaManager
import com.mygdx.game.Managers.LocationManager
import com.mygdx.game.Timer.DefaultTimer

class IceCloneAbility(): CharacterAbility(){
    override val abilityId = AbilityId.ICECLONE
    override val triggerKey = com.badlogic.gdx.Input.Keys.NUM_5
    override val texture = DefaultTextureHandler.getTexture("IceClone.png")

    override val cooldownTimer = DefaultTimer(1f)

    override fun activeAction() {
        val iceClone = IceClone(
            player.currentMiddle,
            Vector2(35f, 65f),
            LocationManager.newDefaultLocation
        )
        AreaManager.getAllGameObjects().forEach {
            if (it is IceClone) {
                it.defaultLocation!!.removeGameObject(it)
            }
        }
        iceClone.defaultLocation!!.addGameObject(iceClone)
    }
}