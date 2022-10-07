package com.mygdx.game.GameObjects.ItemAbilities

import com.mygdx.game.player

class DashAbilityUpgraded: DashAbility() {

    override fun activeAction() {
        super.activeAction()
        player.makeImmune(dashFrames)
    }
}