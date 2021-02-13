package com.mygdx.game.GameObjects.ItemObjects

import com.mygdx.game.DefaultTextureHandler
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.Enums.Layer
import com.mygdx.game.GameObjects.ItemAbilities.ShieldAbility
import com.mygdx.game.LocationImpl

class ShieldItemObject(Position: Vector2, size: Vector2, override val layer: Layer, location: LocationImpl):
    AbilityItemObject(Position, size,location,ShieldAbility(Vector2(0f,0f), Vector2(40f,40f))) {
    override val texture = DefaultTextureHandler.getTexture("shield-front.png")
}