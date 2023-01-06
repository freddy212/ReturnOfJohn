package com.mygdx.game.ItemAbilities

import com.badlogic.gdx.math.Vector2
import com.mygdx.game.*
import com.mygdx.game.AbstractClasses.AbilityId
import com.mygdx.game.AbstractClasses.CharacterAbility
import com.mygdx.game.GameObjects.MoveableEntities.IceClone
import com.mygdx.game.Managers.AreaManager
import com.mygdx.game.Managers.LocationManager
import com.mygdx.game.Timer.DefaultTimer

class IceCloneAbility(): CharacterAbility(){
    override val abilityId = AbilityId.ICECLONE
    override val triggerKey = com.badlogic.gdx.Input.Keys.NUM_4
    override val texture = DefaultTextureHandler.getTexture("man.png")

    override val cooldownTimer = DefaultTimer(1f)

    override fun activeAction() {
        val iceClone = IceClone(
            player.currentMiddle,
            Vector2(35f, 65f),
            LocationManager.newDefaultLocation
        )
        AreaManager.getAllGameObjects().forEach {
            if (it is IceClone) {
                it.removeFromLocation()
            }
        }
        iceClone.defaultLocation!!.addGameObject(iceClone)
    }
}