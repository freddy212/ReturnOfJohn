package com.mygdx.game.GameObjects.ItemObjects

import com.badlogic.gdx.math.Vector2
import com.mygdx.game.DefaultTextureHandler
import com.mygdx.game.Enums.Layer
import com.mygdx.game.GameObjects.ItemAbilities.WaterBallAbility
import com.mygdx.game.Locations.DefaultLocation

class WaterGunItemObject(Position: Vector2, size: Vector2, override val layer: Layer, defaultLocation: DefaultLocation):
    AbilityItemObject(Position, size,
        defaultLocation,
        WaterBallAbility(Vector2(0f,0f),
        Vector2(20f,200f)),
        DefaultTextureHandler.getTexture("WaterGun.png")) {
    init {
       polygon.setPosition(sprite.x - polygon.vertices[0],sprite.y - polygon.vertices[1] - sprite.height)
    }
}