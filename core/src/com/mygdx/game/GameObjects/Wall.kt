package com.mygdx.game.GameObjects

import com.badlogic.gdx.graphics.Texture
import com.mygdx.game.DefaultTextureHandler
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.Enums.Layer
import com.mygdx.game.LocationImpl
import com.mygdx.game.renderRepeatedTexture

class Wall(Position: Vector2, size: Vector2,location:LocationImpl) : GameObject(Position, size) {
    override val texture = DefaultTextureHandler.getTexture("Brick.png")
    override val layer = Layer.BEFORELOCATION
    init {
        texture.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat)
    }
    val wallTopLeft = Pair(location.topleft,Vector2(location.topright.x - location.topleft.x,64f))
    val wallTopRight = Pair(location.bottomright,Vector2(64f,location.topright.y - location.bottomright.y + 64f))
    val wallBottomRight = Pair(Vector2(location.bottomleft.x, location.bottomleft.y - 64f),Vector2(location.bottomright.x - location.bottomleft.x + 64f,64f))
    val wallBottomLeft = Pair(Vector2(location.bottomleft.x - 64f, location.bottomleft.y - 64f),Vector2(64f,location.topleft.y - location.bottomleft.y + 128f))
    override fun render(batch: PolygonSpriteBatch) {
        renderRepeatedTexture(batch,texture,wallTopLeft.first,wallTopLeft.second)
        renderRepeatedTexture(batch,texture,wallTopRight.first,wallTopRight.second)
        renderRepeatedTexture(batch,texture,wallBottomRight.first,wallBottomRight.second)
        renderRepeatedTexture(batch,texture,wallBottomLeft.first,wallBottomLeft.second)

    }
}