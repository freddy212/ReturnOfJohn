package com.mygdx.game.Locations

import com.badlogic.gdx.graphics.Texture
import com.mygdx.game.Collitions.CanMoveCollition
import com.mygdx.game.DefaultTextureHandler
import com.mygdx.game.GameObjects.Wall
import com.mygdx.game.Interfaces.LocationDataStrategy

class DefaultLocationData(textureGiven: Texture = DefaultTextureHandler.getTexture("MainB.jpg")): LocationDataStrategy {
    override val texture = textureGiven
    override val initialization = {
        x: DefaultLocation -> x.addGameObject(Wall(x.initPosition, x.size, x))
    }
    override val collition = CanMoveCollition
}