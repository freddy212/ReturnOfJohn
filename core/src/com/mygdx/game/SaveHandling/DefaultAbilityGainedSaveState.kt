package com.mygdx.game.SaveHandling

import com.mygdx.game.AbstractClasses.CharacterAbility
import com.mygdx.game.player

class DefaultAbilityGainedSaveState(val ability: CharacterAbility): DefaultRemoveObjectSaveState() {
    override fun onLoadAction() {
        super.onLoadAction()
        player.addAbility(ability)
    }
}