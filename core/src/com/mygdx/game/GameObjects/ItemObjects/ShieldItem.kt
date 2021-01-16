package com.mygdx.game.GameObjects.ItemObjects

import com.mygdx.game.DefaultTextureHandler
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.Collitions.AbilityGainedCollition
import com.mygdx.game.Enums.Layer
import com.mygdx.game.GameObjects.ItemAbilities.Shield
import com.mygdx.game.Interfaces.Collition
import com.mygdx.game.LocationImpl

class ShieldItem(Position: Vector2, size: Vector2, override val layer: Layer, location: LocationImpl): GameObject(Position, size,location) {
    override val texture = DefaultTextureHandler.getTexture("shield-front.png")
    override val collition: Collition = AbilityGainedCollition(Shield(Vector2(0f,0f), Vector2(40f,40f)))
}