package com.mygdx.game.ItemAbilities

import com.badlogic.gdx.math.Vector2
import com.mygdx.game.GameObjects.MoveableEntities.Projectiles.Icicle
import com.mygdx.game.player
import com.mygdx.game.plus

class IceCloneAbilityUpgraded: IceCloneAbility() {
    override fun activeAction() {
        if(iceClone in player.defaultLocation!!.gameObjects.List){
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
}