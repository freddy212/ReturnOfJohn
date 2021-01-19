package com.mygdx.game.Locations

import com.badlogic.gdx.graphics.Texture
import com.mygdx.game.Collitions.CanMoveCollition
import com.mygdx.game.Collitions.DOTCollition
import com.mygdx.game.DefaultTextureHandler
import com.mygdx.game.GameObjects.Wall
import com.mygdx.game.Interfaces.LocationStrategy
import com.mygdx.game.LocationImpl

class DamageLocation(textureGiven: Texture = DefaultTextureHandler.getTexture("Lava.jpg")): LocationStrategy {
    override val texture = textureGiven
    override val initialization = {x: LocationImpl -> x.addGameObject(Wall(x.Position, x.size, x))}

    override val collition = DOTCollition
}