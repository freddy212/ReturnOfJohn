package com.mygdx.game.ItemAbilities

import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.AbilityId
import com.mygdx.game.GameObjects.MoveableEntities.Projectiles.Icicle
import com.mygdx.game.Managers.LocationManager
import com.mygdx.game.player
import com.mygdx.game.plus

class IceCloneAbilityUpgraded: IceCloneAbility() {

    override val abilityId = AbilityId.ICECLONEUPGRADE
    override fun activeAction() {
        if(iceClone in LocationManager.ActiveGameObjects){
            val icicle = Icicle(iceClone.currentPosition() + Vector2(-50f, 25f), Vector2(100f,34f),iceClone.defaultLocation!!,
                Vector2(-0.5f,0.5f), iceClone
            )
            val icicle2 = Icicle(iceClone.currentPosition() + Vector2(0f,25f), Vector2(100f,34f),iceClone.defaultLocation!!,Vector2(0.5f,0.5f), iceClone)
            icicle.addToLocation(iceClone.defaultLocation!!)
            icicle2.addToLocation(iceClone.defaultLocation!!)
            iceClone.removeFromLocation()
        }else{
            super.activeAction()
        }
    }
    override fun handleAbilityGained() {
        val iceClone = player.itemAbilities.List.firstOrNull { it is IceCloneAbility }
        player.removeAbility(iceClone)
        super.handleAbilityGained()
    }
}