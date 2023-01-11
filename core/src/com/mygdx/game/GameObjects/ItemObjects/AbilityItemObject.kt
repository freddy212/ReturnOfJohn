package com.mygdx.game.GameObjects.ItemObjects

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.AbstractClasses.CharacterAbility
import com.mygdx.game.Collitions.AbilityGainedCollition
import com.mygdx.game.Enums.Layer
import com.mygdx.game.Locations.DefaultLocation
import com.mygdx.game.Saving.DefaultSaveStateHandler
import com.mygdx.game.Saving.SaveStateEntity

class AbilityItemObject(Position: Vector2, size: Vector2, defaultLocation: DefaultLocation, val ability: CharacterAbility,
                                 override val texture: Texture)
                                 : GameObject(Position, size,defaultLocation), SaveStateEntity by DefaultSaveStateHandler(){
    override val layer: Layer = Layer.ONGROUND
    override val collition = AbilityGainedCollition(ability, this)
}