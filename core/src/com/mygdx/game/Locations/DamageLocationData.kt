package com.mygdx.game.Locations

import com.badlogic.gdx.graphics.Texture
import com.mygdx.game.Collitions.DOTCollition
import com.mygdx.game.DefaultTextureHandler
import com.mygdx.game.Enums.Element
import com.mygdx.game.GameObjects.Other.Wall
import com.mygdx.game.Interfaces.LocationDataStrategy

open class DamageLocationData(textureGiven: String = "Lava.jpg"): LocationDataStrategy {
    override val texture = DefaultTextureHandler.getTexture(textureGiven)
    override val initialization = {
            x: DefaultLocation -> x.addGameObject(Wall(x.initPosition, x.size, x))
    }
    override val collition = DOTCollition()
}