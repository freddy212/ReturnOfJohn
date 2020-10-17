package com.mygdx.game.GameObjects

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.Collitions.IllegalMoveCollition
import com.mygdx.game.Enums.Layer
import com.mygdx.game.renderRepeatedTexture

class Fence(Position: Vector2, size: Vector2,location: com.mygdx.game.LocationImpl): GameObject(Position,size,location) {
    override val texture = Texture("Fence-Start.png")

    init {
        texture.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat)
    }
    override val collition = IllegalMoveCollition
    override val layer = Layer.ONGROUND
    override fun render(batch: PolygonSpriteBatch){
        renderRepeatedTexture(batch,texture,Position,size)
    }
}