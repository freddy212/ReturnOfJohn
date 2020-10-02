package com.mygdx.game.GameObjects

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.GameObject
import com.mygdx.game.Collitions.AbyssCollition
import com.mygdx.game.Collitions.IllegalMoveCollition
import com.mygdx.game.InitSprite
import com.mygdx.game.RectanglePolygon

class Fence(Position: Vector2, size: Vector2): GameObject(Position,size) {
    val start_Texture = Texture("Fence-Start.png")
    override val sprite = InitSprite(start_Texture)
    var textureRegion: TextureRegion

    init {
        start_Texture.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat)
        textureRegion = TextureRegion(start_Texture)
    }
    override val collition = IllegalMoveCollition
    override fun render(batch: PolygonSpriteBatch){
        batch.draw(start_Texture,Position.x,Position.y,0,0,size.x.toInt(),size.y.toInt())
    }

}