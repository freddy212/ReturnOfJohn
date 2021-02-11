package com.mygdx.game.GameObjects.ItemObjects

import com.mygdx.game.DefaultTextureHandler
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.Enums.Layer
import com.mygdx.game.GameObjects.MoveableEntities.WaterGunSpray
import com.mygdx.game.LocationImpl

class WaterGunItemObject(Position: Vector2, size: Vector2, override val layer: Layer, location: LocationImpl):
    AbilityItemObject(Position, size,location,WaterGunSpray(Vector2(0f,0f), Vector2(20f,200f))) {
    override val texture = DefaultTextureHandler.getTexture("WaterGun.png")
    init {
       polygon.setPosition(sprite.x - polygon.vertices[0],sprite.y - polygon.vertices[1] - sprite.height)
    }
}