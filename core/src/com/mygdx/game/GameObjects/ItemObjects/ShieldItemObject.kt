package com.mygdx.game.GameObjects.ItemObjects

import com.badlogic.gdx.math.Vector2
import com.mygdx.game.DefaultTextureHandler
import com.mygdx.game.Enums.Layer
import com.mygdx.game.GameObjects.ItemAbilities.ShieldAbility
import com.mygdx.game.Locations.DefaultLocation

class ShieldItemObject(Position: Vector2, size: Vector2, override val layer: Layer, defaultLocation: DefaultLocation):
    AbilityItemObject(Position, size,defaultLocation,ShieldAbility(Vector2(0f,0f), Vector2(20f,40f))) {
    override val texture = DefaultTextureHandler.getTexture("shield-front.png")
}