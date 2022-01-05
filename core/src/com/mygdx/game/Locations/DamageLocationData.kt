package com.mygdx.game.Locations

import com.badlogic.gdx.graphics.Texture
import com.mygdx.game.Collitions.DOTCollition
import com.mygdx.game.DefaultTextureHandler
import com.mygdx.game.GameObjects.Wall
import com.mygdx.game.Interfaces.LocationDataStrategy

open class DamageLocationData(textureGiven: Texture = DefaultTextureHandler.getTexture("Lava.jpg"),
): LocationDataStrategy {
    override val texture = textureGiven
    override val initialization = {
            x: DefaultLocation -> x.addGameObject(Wall(x.Position, x.size, x))
    }
    override val collition = DOTCollition
}