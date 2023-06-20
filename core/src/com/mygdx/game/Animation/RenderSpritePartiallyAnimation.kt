package com.mygdx.game.Animation

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.Enums.Direction
import com.mygdx.game.Enums.Layer

class RenderSpritePartiallyAnimation(
    override val duration: Int,
    override val animationAction: () -> Unit,
    val texture: Texture,
    val direction: Direction,
    val initPos: Vector2,
    val size: Vector2,
) : DefaultAnimation() {

    override val layer = Layer.ONGROUND

    var visibilityValueX = ((size.x / duration) * currentFrame).toFloat()
    var visibilityValueY = ((size.y / duration) * currentFrame).toFloat()

    override var actionFrame = 0

    init {
    }

    override fun render(batch: PolygonSpriteBatch) {
        visibilityValueX = ((size.x / duration) * currentFrame).toFloat()
        visibilityValueY = ((size.y / duration) * currentFrame).toFloat()
        val visibilityVector = getVisibilityVector()
        //batch.draw(texture, initPos.x, initPos.y, 0,0, visibilityVector.x.toInt(), visibilityVector.y.toInt())
        batch.draw(texture, initPos.x, initPos.y - visibilityValueY, size.x, visibilityVector.y,0,0,visibilityVector.x.toInt(),visibilityVector.y.toInt(),false,false)
    }

    fun getVisibilityVector(): Vector2 {
        return when (direction) {
            Direction.UP -> Vector2(size.x, visibilityValueY)
            Direction.DOWN -> Vector2(texture.width.toFloat(), visibilityValueY)
            Direction.RIGHT -> Vector2(visibilityValueX, size.y)
            Direction.LEFT -> Vector2(visibilityValueX, size.y)
        }
    }
}