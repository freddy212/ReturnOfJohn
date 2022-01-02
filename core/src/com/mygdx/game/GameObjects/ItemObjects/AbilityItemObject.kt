package com.mygdx.game.GameObjects.ItemObjects

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.AbstractClasses.CharacterAbility
import com.mygdx.game.Collitions.AbilityGainedCollition
import com.mygdx.game.Enums.Layer
import com.mygdx.game.Locations.DefaultLocation
import com.mygdx.game.SaveHandling.DefaultAbilityGainedSaveState
import com.mygdx.game.SaveHandling.DefaultRemoveObjectSaveState
import com.mygdx.game.SaveState.SaveStateEntity
import com.mygdx.game.player

open class AbilityItemObject(Position: Vector2, size: Vector2, defaultLocation: DefaultLocation, val ability: CharacterAbility,
                                 override val texture: Texture)
                                 : GameObject(Position, size,defaultLocation), SaveStateEntity by DefaultAbilityGainedSaveState(ability){
    override val collition = AbilityGainedCollition(ability)
    override val layer: Layer = Layer.ONGROUND
}