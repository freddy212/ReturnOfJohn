import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.AbstractClasses.CharacterAbility
import com.mygdx.game.Interfaces.Timer
import com.mygdx.game.ItemAbilities.DashAbilityUpgraded
import com.mygdx.game.ItemAbilities.IceCloneAbilityUpgraded
import com.mygdx.game.ItemAbilities.ShieldAbilityUpgraded
import com.mygdx.game.Managers.UIRendererManager
import com.mygdx.game.Timer.DefaultTimer
import com.mygdx.game.font

class ToolTip (val key: Char, val characterAbility: CharacterAbility) {
    var show = true
    val shapeRenderer = UIRendererManager.uiShapeRenderer
    fun render(position: Vector2,UIbatch: PolygonSpriteBatch){
        if(characterAbility.showToolTip){
            val sprite = Sprite(characterAbility.toolTipTexture)
            sprite.setSize(50f,50f)
            val alphaValue = if(characterAbility.cooldownTimer.cooldownAvailable()) 1f else 0.5f
            sprite.setPosition(position.x,position.y)
            sprite.setAlpha(alphaValue)
            sprite.draw(UIbatch)
            font.draw(UIbatch,key.toString(),sprite.x + sprite.width / 3, sprite.y + sprite.height + 20f)

            if(characterAbility is DashAbilityUpgraded || characterAbility is ShieldAbilityUpgraded || characterAbility is IceCloneAbilityUpgraded){
                UIbatch.end()
                Gdx.gl.glLineWidth(3f);
                shapeRenderer.begin(ShapeRenderer.ShapeType.Line)
                shapeRenderer.color = Color.GREEN
                shapeRenderer.circle(sprite.x + sprite.width / 2, sprite.y + sprite.height / 2, 30f)
                shapeRenderer.end()
                Gdx.gl.glLineWidth(1f);
                UIbatch.begin()
            }
        }
    }
}