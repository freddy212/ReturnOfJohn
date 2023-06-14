package com.mygdx.game.ItemAbilities

import com.mygdx.game.AbstractClasses.AbilityId
import com.mygdx.game.Collitions.ShieldUpgradedCollition
import com.mygdx.game.player

class ShieldAbilityUpgraded: ShieldAbility() {

    override val abilityId = AbilityId.SHIELDUPGRADE

    init {
        this.shield.collition = ShieldUpgradedCollition(this.shield)
    }

    override fun handleAbilityGained() {
        val shieldAbility = player.itemAbilities.List.firstOrNull { it is ShieldAbility }
        player.removeAbility(shieldAbility)
        super.handleAbilityGained()
    }
}