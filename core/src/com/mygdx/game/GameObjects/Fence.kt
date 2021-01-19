package com.mygdx.game.GameObjects

import com.badlogic.gdx.graphics.Texture
import com.mygdx.game.DefaultTextureHandler
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.Collitions.IllegalMoveCollition
import com.mygdx.game.Enums.Layer
import com.mygdx.game.LocationImpl
import com.mygdx.game.renderRepeatedTexture

class Fence(Position: Vector2, size: Vector2,location: LocationImpl, override val texture: Texture = DefaultTextureHandler.getTexture("Fence-Start.png")): GameObject(Position,size,location) {

    init {
        texture.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat)
    }
    override val collition = IllegalMoveCollition
    override val layer = Layer.ONGROUND
    override fun render(batch: PolygonSpriteBatch){
        renderRepeatedTexture(batch,texture,Position,size)
    }
}