import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.Interfaces.Timer
import com.mygdx.game.Timer.DefaultTimer
import com.mygdx.game.font

class ToolTip (val sprite: Sprite, val key: Char, val timer: Timer) {
    init {
        sprite.setSize(50f,50f)
    }
    fun render(position: Vector2,UIbatch: PolygonSpriteBatch){
        val alphaValue = if(timer.cooldownAvailable()) 1f else 0.5f
        sprite.setPosition(position.x,position.y)
        sprite.setAlpha(alphaValue)
        sprite.draw(UIbatch)
        font.draw(UIbatch,key.toString(),sprite.x + sprite.width / 3, sprite.y + sprite.height + 20f)
    }
}