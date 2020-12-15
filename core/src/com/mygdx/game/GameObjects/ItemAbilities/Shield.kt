package com.mygdx.game.GameObjects.ItemAbilities

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.DefaultPositionChange
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.Enums.Layer
import com.mygdx.game.Interfaces.DynamicEntity
import com.mygdx.game.LocationImpl

class Shield(Position: Vector2, size: Vector2, location: LocationImpl?): GameObject(Position, size, location), DynamicEntity by DefaultPositionChange{
    override val texture: Texture
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
    override val layer: Layer
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

}