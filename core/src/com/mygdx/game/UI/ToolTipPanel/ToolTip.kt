import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.font

class ToolTip (val sprite: Sprite, val key: Char) {
    init {
        sprite.setSize(50f,50f)
    }
    fun render(position: Vector2,UIbatch: PolygonSpriteBatch){
        sprite.setPosition(position.x,position.y)
        sprite.draw(UIbatch)
        font.draw(UIbatch,key.toString(),sprite.x + sprite.width / 3, sprite.y + sprite.height + 20f)
    }
}