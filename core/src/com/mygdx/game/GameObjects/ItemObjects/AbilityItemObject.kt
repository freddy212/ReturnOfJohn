package com.mygdx.game.GameObjects.ItemObjects

import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.AbstractClasses.CharacterAbility
import com.mygdx.game.Collitions.AbilityGainedCollition
import com.mygdx.game.Locations.DefaultLocation
import com.mygdx.game.SaveHandling.DefaultRemoveObjectSaveState
import com.mygdx.game.SaveState.SaveStateEntity
import com.mygdx.game.player

abstract class AbilityItemObject(Position: Vector2, size: Vector2, defaultLocation: DefaultLocation, val ability: CharacterAbility,
                                 private val removeObjectSaveState:DefaultRemoveObjectSaveState = DefaultRemoveObjectSaveState())
                                 : GameObject(Position, size,defaultLocation), SaveStateEntity by removeObjectSaveState{
    override val collition = AbilityGainedCollition(ability)
    override fun onLoadAction() {
        removeObjectSaveState.onLoadAction()
        player.addAbility(ability)
    }
}