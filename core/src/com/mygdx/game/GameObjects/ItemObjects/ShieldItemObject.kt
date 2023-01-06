package com.mygdx.game.GameObjects.ItemObjects

import com.badlogic.gdx.math.Vector2
import com.mygdx.game.DefaultTextureHandler
import com.mygdx.game.ItemAbilities.ShieldAbility
import com.mygdx.game.Locations.DefaultLocation

class ShieldItemObject(Position: Vector2, size: Vector2, defaultLocation: DefaultLocation):
    AbilityItemObject(Position,
                      size,
                      defaultLocation,
                      ShieldAbility(),
                      DefaultTextureHandler.getTexture("shield-front.png")) {
}