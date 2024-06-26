package com.mygdx.game.GameObjects.Other

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.DefaultTextureHandler
import com.mygdx.game.Enums.Layer
import com.mygdx.game.Interfaces.getAreaColor
import com.mygdx.game.Locations.DefaultLocation
import com.mygdx.game.renderRepeatedTexture
import com.badlogic.gdx.graphics.Color.WHITE
import java.awt.Color

class Wall(Position: Vector2, size: Vector2, defaultLocation: DefaultLocation) : GameObject(Position, size) {
    override val texture = DefaultTextureHandler.getTexture("Brick.png")
    override val layer = Layer.BEFORELOCATION
    override val shouldCollide = false

    lateinit var color: com.badlogic.gdx.graphics.Color

    init {
        texture.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat)
        this.onLocationEnterActions.add { color = getAreaColor(defaultLocation.areaIdentifier) }
    }
    val wallTopLeft = Pair(defaultLocation.topleft,Vector2(defaultLocation.topright.x - defaultLocation.topleft.x,64f))
    val wallTopRight = Pair(defaultLocation.bottomright,Vector2(64f,defaultLocation.topright.y - defaultLocation.bottomright.y + 64f))
    val wallBottomRight = Pair(Vector2(defaultLocation.bottomleft.x, defaultLocation.bottomleft.y - 64f),Vector2(defaultLocation.bottomright.x - defaultLocation.bottomleft.x + 64f,64f))
    val wallBottomLeft = Pair(Vector2(defaultLocation.bottomleft.x - 64f, defaultLocation.bottomleft.y - 64f),Vector2(64f,defaultLocation.topleft.y - defaultLocation.bottomleft.y + 128f))
    override fun render(batch: PolygonSpriteBatch) {
        batch.color = color
        renderRepeatedTexture(batch,texture,wallTopLeft.first,wallTopLeft.second)
        renderRepeatedTexture(batch,texture,wallTopRight.first,wallTopRight.second)
        renderRepeatedTexture(batch,texture,wallBottomRight.first,wallBottomRight.second)
        renderRepeatedTexture(batch,texture,wallBottomLeft.first,wallBottomLeft.second)
        batch.color = WHITE

    }
}