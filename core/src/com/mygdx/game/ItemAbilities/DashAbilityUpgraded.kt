package com.mygdx.game.ItemAbilities

import com.mygdx.game.AbstractClasses.AbilityId
import com.mygdx.game.player

class DashAbilityUpgraded: DashAbility() {

    override val abilityId = AbilityId.DASHUPGRADE

    override fun activeAction() {
        super.activeAction()
        player.makeImmune(dashFrames)
    }

    override fun handleAbilityGained() {
        val dashAbility = player.itemAbilities.List.firstOrNull { it is DashAbility }
        player.removeAbility(dashAbility)
        super.handleAbilityGained()
    }
}