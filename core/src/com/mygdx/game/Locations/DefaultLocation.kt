package com.mygdx.game.Locations

import com.badlogic.gdx.graphics.Texture
import com.mygdx.game.Collitions.CanMoveCollition
import com.mygdx.game.DefaultTextureHandler
import com.mygdx.game.GameObjects.Wall
import com.mygdx.game.Interfaces.LocationStrategy
import com.mygdx.game.Interfaces.MoveCollition
import com.mygdx.game.LocationImpl

class DefaultLocation(textureGiven: Texture = DefaultTextureHandler.getTexture("MainB.jpg")): LocationStrategy {
    override val texture = textureGiven
    override val initialization = {
        x:LocationImpl -> x.addGameObject(Wall(x.Position, x.size, x))
    }
    override val collition = CanMoveCollition
}